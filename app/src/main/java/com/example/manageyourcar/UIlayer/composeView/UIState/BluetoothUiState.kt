package com.example.manageyourcar.UIlayer.composeView.UIState

import com.example.manageyourcar.domainLayer.bluetooth.BluetoothDevice


data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices: List<BluetoothDevice> = emptyList(),
    val isConnected: Boolean = false,
    val isConnecting: Boolean = false,
    val errorMessage: String? = null,
)