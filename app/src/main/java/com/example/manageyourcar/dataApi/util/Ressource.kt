package com.example.manageyourcar.dataApi.util

sealed class Ressource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Ressource<T>(data)
    class Loading<T>() : Ressource<T>()
    class Error<T>(message: String, data: T? = null): Ressource<T>(data, message)
}
