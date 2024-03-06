package com.example.manageyourcar.UIlayer

import android.app.Application
import com.example.manageyourcar.BuildConfig
import com.example.manageyourcar.UIlayer.viewEvent.UIUtil
import com.example.manageyourcar.dataLayer.di.injectFeature
import org.koin.android.ext.koin.androidContext
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
            "dev" -> UIUtil(applicationContext).displayToast("dev")
            "recette" -> UIUtil(applicationContext).displayToast("recette")
        }
    }

    companion object {

    }
}