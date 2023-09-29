package com.example.manageyourcar.view

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import com.example.manageyourcar.di.appModule
import com.example.manageyourcar.model.Ressource
import com.example.manageyourcar.model.requestApi
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import kotlin.coroutines.coroutineContext

class AppApplication : Application() {

    val TAG : String="AppApplication"

    val api by inject<requestApi>()
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