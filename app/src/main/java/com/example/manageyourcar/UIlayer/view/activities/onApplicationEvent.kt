package com.example.manageyourcar.UIlayer.view.activities

sealed interface OnApplicationEvent {
    data class OnInternetStatusChanged(val status : Boolean): OnApplicationEvent
}