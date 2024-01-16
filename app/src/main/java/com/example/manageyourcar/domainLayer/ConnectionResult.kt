package com.example.manageyourcar.domainLayer

import com.example.manageyourcar.domainLayer.bluetooth.BluetoothDeviceDomain
import java.io.InputStream
import java.io.OutputStream

sealed interface ConnectionResult {
    data class ConnectionEstablished(
        val device: BluetoothDeviceDomain,
        val inputStream: InputStream,
        val outputStream: OutputStream
    ) : ConnectionResult

    data class Error(val message: String) : ConnectionResult
}