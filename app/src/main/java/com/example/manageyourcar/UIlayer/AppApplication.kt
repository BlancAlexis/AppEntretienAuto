package com.example.manageyourcar.UIlayer

import android.app.Application
import com.example.manageyourcar.dataLayer.di.injectFeature
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin

class AppApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            injectFeature()
        }
    }
}