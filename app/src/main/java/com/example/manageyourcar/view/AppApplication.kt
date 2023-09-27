package com.example.manageyourcar.view

import android.app.Application
import com.example.manageyourcar.di.appModule
import com.example.manageyourcar.model.GetVehiculeBySivUseCase
import com.example.manageyourcar.model.Ressource
import com.example.manageyourcar.model.requestApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppApplication : Application() {

    val getVehiculeBySivUseCase by inject<GetVehiculeBySivUseCase>()

    val api by inject<requestApi>()
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(appModule)
        }
        runBlocking {
            coroutineScope {
                launch {
                    getVehiculeBySivUseCase.getVehiculeBySiv("ZPBUA1ZL9KLA00848").collect { result ->
                        when (result) {
                            is Ressource.Loading ->
                                print("yoti")
                            is Ressource.Error ->
                                print("yota"+result.data)

                            is Ressource.Success ->
                                    print("yoto"+result.data)
                        }
                    }
                }
            }
        }
    }
}