package com.example.manageyourcar.dataLayer

import android.location.Location

interface GlobalEvent {
    fun getEvent(): String {
        return "Event"
    }

    fun onInternetConnectionLost()
    fun onInternetConnectionAvailable()
    fun onLocationChanged(location: Location)
}