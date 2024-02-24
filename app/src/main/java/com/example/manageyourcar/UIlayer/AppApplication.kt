package com.example.manageyourcar.UIlayer

import android.app.Application
import android.util.Log
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.di.injectFeature
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.domainLayer.useCaseRoom.car.AddCarRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber
import java.util.Date

class AppApplication() : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            injectFeature()
        }
        val addCar: AddCarRoomUseCase = AddCarRoomUseCase()
        GlobalScope.launch {
            addCar.addCarToRoom(
                CarLocal(
                    "",
                    "bmw",
                    "série 3",
                    Date().toString(),
                    "essence",
                    "3",
                    "W16",
                    125,
                    340,
                    200,
                    listOf(0),
                    1
                )
            )

        }
        println("prout")
        val getCarRoomUseCase = GetUserCarsUseCase()
        GlobalScope.launch {
            getCarRoomUseCase.invoke().collect { result ->
                when (result) {
                    is Ressource.Error -> {
                        println("lolerrror")
                        Timber.e(result.error)
                    }
                    is Ressource.Success -> {
                        println("lolsucces")

                        result.data?.forEach() {
                            Log.i("prout","it.brand")
                        }
                    }

                    else -> {}

                }


        }


    }
}


companion object {

}
}