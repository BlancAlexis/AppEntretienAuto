package com.example.manageyourcar.dataLayer

import android.location.Location
import com.example.manageyourcar.dataLayer.model.CarLocal

interface GlobalEvent {
    fun getEvent(): String {
        return "Event"
    }
    fun getUserCarList(key: String): Any?
    suspend fun saveUserCarList(carList : List<CarLocal>)
    fun onInternetInternetLost()
    fun onLocationChanged(location: Location)
}