package com.example.manageyourcar.domainLayer.mappers

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.example.manageyourcar.domainLayer.bluetooth.BluetoothDeviceDomain

object BluetoothDeviceMappers {
    @SuppressLint("MissingPermission")
    fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
        return BluetoothDeviceDomain(
            name = name,
            address = address
        )
    }
}
