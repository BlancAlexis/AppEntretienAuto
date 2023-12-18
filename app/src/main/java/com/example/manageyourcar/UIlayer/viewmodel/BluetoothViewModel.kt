package com.example.manageyourcar.UIlayer.viewmodel


import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.composeView.UIState.BluetoothUiState
import com.example.manageyourcar.UIlayer.composeView.UIState.SignInUiState
import com.example.manageyourcar.domainLayer.ConnectionResult
import com.example.manageyourcar.domainLayer.bluetooth.BluetoothController
import com.example.manageyourcar.domainLayer.bluetooth.BluetoothDevice
import com.example.manageyourcar.domainLayer.bluetooth.BluetoothDeviceDomain
import com.example.manageyourcar.domainLayer.bluetooth.ConnectedBluetoothDevice
import com.github.eltonvs.obd.command.ObdCommand
import com.github.eltonvs.obd.command.control.VINCommand
import com.github.eltonvs.obd.command.engine.RPMCommand
import com.github.eltonvs.obd.command.engine.SpeedCommand
import com.github.eltonvs.obd.connection.ObdDeviceConnection
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BluetoothViewModel : ViewModel(), KoinComponent {
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
                    navController.navigate(R.id.action_connectObdFragment_to_OBDFragment)

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

    fun onEvent(onBluetoothDeviceEvent: onBluetoothDeviceEvent) {
when (val result=onBluetoothDeviceEvent) {
            is onBluetoothDeviceEvent.OnBluetoothDeviceClick -> connectToDevice(result.bluetoothDevice)
            is onBluetoothDeviceEvent.OnBluetoothDeviceLongClick -> {
            }
            is onBluetoothDeviceEvent.OnBluetoothDeviceConnectClick -> {
            }
            is onBluetoothDeviceEvent.OnBluetoothDeviceDisconnectClick -> {
            }
            is onBluetoothDeviceEvent.OnBluetoothDeviceScanClick -> {
                startScan()
            }
            is onBluetoothDeviceEvent.OnBluetoothDeviceStopScanClick -> {
                stopScan()
            }
        }
    }
}

sealed interface onBluetoothDeviceEvent {
    data class OnBluetoothDeviceClick(val bluetoothDevice : BluetoothDevice) : onBluetoothDeviceEvent
    object OnBluetoothDeviceLongClick : onBluetoothDeviceEvent
    object OnBluetoothDeviceConnectClick : onBluetoothDeviceEvent
    object OnBluetoothDeviceDisconnectClick : onBluetoothDeviceEvent
    object OnBluetoothDeviceScanClick : onBluetoothDeviceEvent
    object OnBluetoothDeviceStopScanClick : onBluetoothDeviceEvent
}