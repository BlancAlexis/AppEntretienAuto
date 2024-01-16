package com.example.manageyourcar.UIlayer.view.fragments

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log

class MyLocationListener : LocationListener {

    override fun onLocationChanged(location: Location) {
        Log.i("TAG", "onLocationChanged: dddd ${location.latitude}${location.longitude}")
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle?) {
        // Traiter le changement de statut du fournisseur de localisation
    }

    override fun onProviderEnabled(provider: String) {
        // Traiter l'activation du fournisseur de localisation
    }

    override fun onProviderDisabled(provider: String) {
        // Traiter la désactivation du fournisseur de localisation
    }
}