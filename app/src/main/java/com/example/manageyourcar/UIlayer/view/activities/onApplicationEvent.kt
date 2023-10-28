package com.example.manageyourcar.UIlayer.view.activities

import android.database.Observable

sealed interface OnApplicationEvent {
    data class OnInternetStatusChanged(val status: Boolean) : OnApplicationEvent
}