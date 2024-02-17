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


    companion object {
        lateinit var instance: AppApplication
            private set
    }
}