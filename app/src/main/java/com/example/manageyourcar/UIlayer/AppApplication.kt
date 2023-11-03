package com.example.manageyourcar.UIlayer

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import com.example.manageyourcar.UIlayer.view.activities.OnApplicationEvent
import com.example.manageyourcar.dataLayer.di.injectFeature
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.dataLayer.model.MaintenanceService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import java.util.Date

class AppApplication : Application() {
    val TAG: String = "AppApplication"

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            injectFeature()
        }
        registerInternetListener()

        val entretien=Entretien(23,66, Date(166), MaintenanceService.Freins())

        when(entretien.service){
            is MaintenanceService.Freins -> entretien.service.category
            is MaintenanceService.Pneus -> TODO()
            is MaintenanceService.Vidange -> TODO()
        }
    }

    fun registerInternetListener() {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.let {
            it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    OnApplicationEvent.OnInternetStatusChanged(true)
                    Log.i(TAG, "onAvailable: Connecté à internet!")
                }

                override fun onLost(network: Network) {
                    OnApplicationEvent.OnInternetStatusChanged(false)
                    Log.i(TAG, "onLost: Aucune connexion internet...")
                }
            })
        }
    }
}