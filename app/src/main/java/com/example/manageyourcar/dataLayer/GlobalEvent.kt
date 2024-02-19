package com.example.manageyourcar.dataLayer

interface GlobalEvent {
    fun onInternetConnectionLost()
    fun onInternetConnectionAvailable()
}