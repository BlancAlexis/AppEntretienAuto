package com.example.manageyourcar.UIlayer.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.domainLayer.bluetooth.ConnectedBluetoothDevice
import com.github.eltonvs.obd.command.control.VINCommand
import com.github.eltonvs.obd.command.engine.RPMCommand
import com.github.eltonvs.obd.command.engine.SpeedCommand
import com.github.eltonvs.obd.connection.ObdDeviceConnection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class OBDViewModel : ViewModel(), KoinComponent {
    //tab avec toutes les données ou un  livedata pour chaque donnée
    val connectedBluetoothDevice = ConnectedBluetoothDevice
    val obdConnection : ObdDeviceConnection by lazy {
        connectedBluetoothDevice.let {bluetoothDevice ->
            if (bluetoothDevice.inputStream != null || bluetoothDevice.outputStream != null) {
                ObdDeviceConnection(bluetoothDevice.inputStream!!, bluetoothDevice.outputStream!!)
            }
            else {
                throw Exception("No bluetooth device connected")
            }
       }
    }


    fun getRPM() {
        viewModelScope.launch {
            while (true) {
                Log.i("OBDViewModel",(Integer.parseInt(obdConnection.run(RPMCommand()).value, 16)).toString())
                delay(50)
            }
        }
    }
}