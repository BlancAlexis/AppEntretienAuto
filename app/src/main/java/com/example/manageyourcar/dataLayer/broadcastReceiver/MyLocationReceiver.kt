package com.example.manageyourcar.dataLayer.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log

class MyLocationReceiver : BroadcastReceiver(), LocationListener {

    override fun onLocationChanged(location: Location) {
        Log.i("TAG", "onLocationChanged: ")
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        Log.i("TAG", "onStatusChanged: ")
    }

    override fun onProviderEnabled(provider: String) {
        // Traiter l'activation du fournisseur de localisation
    }

    override fun onProviderDisabled(provider: String) {
        // Traiter la désactivation du fournisseur de localisation
    }

    companion object {
        const val ACTION_LOCATION_CHANGED = "com.example.myapplication.location_changed"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_LOCATION_CHANGED) {
            Log.i("TAG", "onReceive: ")
        }
    }
}