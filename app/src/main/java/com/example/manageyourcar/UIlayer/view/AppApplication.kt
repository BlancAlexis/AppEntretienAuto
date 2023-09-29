package com.example.manageyourcar.UIlayer.view

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import com.example.manageyourcar.dataLayer.di.appModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppApplication : Application() {

    val TAG : String="AppApplication"

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(appModule)
        }
        registerInternetListener()
    }

    fun registerInternetListener(){
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager?.let {
            it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    Log.i(TAG, "onAvailable: Connecté à internet!")                }
                override fun onLost(network: Network) {
                    Log.i(TAG, "onLost: Aucune connexion internet...")                }
            })
        }
    }
}