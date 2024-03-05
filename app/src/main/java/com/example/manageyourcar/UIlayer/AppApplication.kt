package com.example.manageyourcar.UIlayer

import android.app.Application
import com.example.manageyourcar.BuildConfig
import com.example.manageyourcar.dataLayer.di.injectFeature
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            injectFeature()
        }

        // Test buildVariant
        when (BuildConfig.FLAVOR) {
            "dev" -> println("dev")
            "recette" -> println("recette")
        }
    }
    companion object {

    }
}