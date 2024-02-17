package com.example.manageyourcar.UIlayer

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import com.example.manageyourcar.UIlayer.view.activities.MainActivity
import com.example.manageyourcar.dataLayer.GlobalEvent
import com.example.manageyourcar.dataLayer.ListenerInternet
import com.example.manageyourcar.dataLayer.di.injectFeature
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.GlobalContext.startKoin

class AppApplication : Application(), KoinComponent {
    val TAG: String = "AppApplication"

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@AppApplication)
            injectFeature()
        }

        ListenerInternet(get<GlobalEvent>())

    }

    fun registerInternetListener( globalEvent: GlobalEvent) {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.let {
            it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    Log.i(TAG, "onAvailable: Connecté à internet!")
                    globalEvent.onInternetConnectionLost()

                }

                override fun onLost(network: Network) {
                    Log.i(TAG, "onLost: Aucune connexion internet...")
                    globalEvent.onInternetConnectionLost()
                }
            })
        }
    }


    companion object {
        lateinit var instance: AppApplication
            private set
    }
}