package com.example.manageyourcar.dataLayer

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import org.koin.core.component.KoinComponent
import timber.log.Timber

class ListenerInternet(private val globalEvent: GlobalEvent, private val application: Application) :
    KoinComponent {
    init {
        registerInternetListener()
    }

    private fun registerInternetListener() {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Timber.i("onAvailable: Connecté à internet!")
                globalEvent.onInternetConnectionAvailable()
            }

            override fun onLost(network: Network) {
                Timber.i("onLost: Déconnecté d'internet!")
                globalEvent.onInternetConnectionLost()
            }
        })
    }
}