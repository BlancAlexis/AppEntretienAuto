package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.manageyourcar.domainLayer.bluetooth.ConnectedBluetoothDevice
import org.koin.core.component.KoinComponent

class OBDViewModel : ViewModel(), KoinComponent {
    //tab avec toutes les données ou un  livedata pour chaque donnée
    val connectedBluetoothDevice = ConnectedBluetoothDevice
}