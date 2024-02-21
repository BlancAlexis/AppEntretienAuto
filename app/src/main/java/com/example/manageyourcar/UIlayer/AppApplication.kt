package com.example.manageyourcar.UIlayer

import android.app.Application
import com.example.manageyourcar.dataLayer.GlobalEvent
import com.example.manageyourcar.dataLayer.ListenerInternet
import com.example.manageyourcar.dataLayer.di.injectFeature
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin

class AppApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@AppApplication)
            injectFeature()
        }
    }


    companion object {
        lateinit var instance: AppApplication
            private set
    }
}