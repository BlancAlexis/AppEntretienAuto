package com.example.manageyourcar.dataLayer

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.UIlayer.view.activities.MainActivity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object ListenerInternet : KoinComponent {

    fun registerInternetListener(globalEvent: GlobalEvent) {
        val connectivityManager =
            AppApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.i("TAG", "onAvailable: Connecté à internet!")
                globalEvent.onInternetConnectionAvailable()
            }

            override fun onLost(network: Network) {
                globalEvent.onInternetConnectionLost()
            }
        })
    }
}