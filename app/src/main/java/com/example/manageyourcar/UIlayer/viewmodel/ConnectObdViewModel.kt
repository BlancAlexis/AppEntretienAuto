package com.example.manageyourcar.UIlayer.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.manageyourcar.UIlayer.UIState.BluetoothUiState
import com.example.manageyourcar.domainLayer.bluetooth.BluetoothController
import com.example.manageyourcar.domainLayer.bluetooth.BluetoothDevice
import com.example.manageyourcar.domainLayer.bluetooth.BluetoothDeviceDomain
import com.example.manageyourcar.domainLayer.bluetooth.ConnectedBluetoothDevice
import com.example.manageyourcar.domainLayer.bluetooth.ConnectionResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ConnectObdViewModel : ViewModel(), KoinComponent {
    private lateinit var navController: NavController
    private val bluetoothController by inject<BluetoothController>()


    private val _state = MutableStateFlow(BluetoothUiState())
    val state = combine(
        bluetoothController.scannedDevices,
        bluetoothController.pairedDevices,
        _state
    ) { scannedDevices, pairedDevices, state ->
        state.copy(
            scannedDevices = scannedDevices,
            pairedDevices = pairedDevices
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    private var deviceConnectionJob: Job? = null

    init {
        bluetoothController.isConnected.onEach { isConnected ->
            _state.update { it.copy(isConnected = isConnected) }
        }.launchIn(viewModelScope)

        bluetoothController.errors.onEach { error ->
            _state.update {
                it.copy(
                    errorMessage = error
                )
            }
        }.launchIn(viewModelScope)
    }

    fun connectToDevice(device: BluetoothDeviceDomain) {
        println("Connecting to device: $device")
        _state.update { it.copy(isConnecting = true) }
        deviceConnectionJob = bluetoothController
            .connectToDevice(device)
            .listen()
    }

    fun disconnectFromDevice() {
        deviceConnectionJob?.cancel()
        bluetoothController.closeConnection()
        _state.update {
            it.copy(
                isConnecting = false,
                isConnected = false
            )
        }
    }


//    fun waitForIncomingConnections() {
//        _state.update { it.copy(isConnecting = true) }
//        deviceConnectionJob = bluetoothController
//            .startBluetoothServer()
//            .listen()
//    }

    fun startScan() {
        bluetoothController.startDiscovery()
    }

    fun stopScan() {
        bluetoothController.stopDiscovery()
    }

    private fun Flow<ConnectionResult>.listen(): Job {
        return onEach { result ->
            when (result) {
                is ConnectionResult.ConnectionEstablished -> {
                    ConnectedBluetoothDevice.inputStream = result.inputStream
                    ConnectedBluetoothDevice.bluetoothDevice = result.device
                    ConnectedBluetoothDevice.outputStream = result.outputStream
                    // navController.navigate(R.id.action_connectObdFragment_to_OBDFragment)

                    /*  _state.update {
                          it.copy(
                              isConnected = true,
                              isConnecting = false,
                              errorMessage = null
                          )
                      }*/
                }

                is ConnectionResult.Error -> {
                    _state.update {
                        it.copy(
                            isConnected = false,
                            isConnecting = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
            .catch { throwable ->
                bluetoothController.closeConnection()
                _state.update {
                    it.copy(
                        isConnected = false,
                        isConnecting = false,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        bluetoothController.release()
    }

    fun onEvent(onBluetoothDeviceEvent: OnBluetoothDeviceEvent) {
        when (val result = onBluetoothDeviceEvent) {
            is OnBluetoothDeviceEvent.OnBluetoothDeviceClick -> connectToDevice(result.bluetoothDevice)
            is OnBluetoothDeviceEvent.OnBluetoothDeviceLongClick -> {
            }

            is OnBluetoothDeviceEvent.OnBluetoothDeviceConnectClick -> {
            }

            is OnBluetoothDeviceEvent.OnBluetoothDeviceDisconnectClick -> {
            }
        }
    }
}

sealed interface OnBluetoothDeviceEvent {
    data class OnBluetoothDeviceClick(val bluetoothDevice: BluetoothDevice) : OnBluetoothDeviceEvent
    object OnBluetoothDeviceLongClick : OnBluetoothDeviceEvent
    object OnBluetoothDeviceConnectClick : OnBluetoothDeviceEvent
    object OnBluetoothDeviceDisconnectClick : OnBluetoothDeviceEvent
}