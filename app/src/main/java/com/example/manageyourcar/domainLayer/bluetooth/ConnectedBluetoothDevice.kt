package com.example.manageyourcar.domainLayer.bluetooth

import java.io.InputStream
import java.io.OutputStream

object ConnectedBluetoothDevice {
    var bluetoothDevice: BluetoothDevice? = null
    var inputStream: InputStream? = null
    var outputStream: OutputStream? = null
}