package com.example.manageyourcar.UIlayer

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.dataLayer.dataLayerFirebase.carRemoteDataFirebaseSourceImpl
import com.example.manageyourcar.dataLayer.dataLayerFirebase.remoteDataFirebaseSource
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.di.injectFeature
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.useCaseRoom.car.AddCarRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.DeleteCarRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import kotlinx.coroutines.Dispatchers
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

        val deleteCarRoomUseCase = DeleteCarRoomUseCase()
val cacheManagerRepository = get<CacheManagerRepository> ()
        GlobalScope.launch(Dispatchers.IO) {
            when (val result = cacheManagerRepository.getUserCarList()) {
                is Ressource.Error -> {
                }

                is Ressource.Success -> {
                    if (result.data.isNullOrEmpty()) {
                        return@launch
                    } else {
                        result.data.forEach {
                            Log.i("cacheCar", it.carID)
                        }
                        val dataSource = get<remoteDataFirebaseSource> ()
                       // dataSource.deleteCar(result.data.last())
                        dataSource.updateCar(result.data.last().copy(mileage = listOf(0,10224)))
                    }
                }

                else -> {}
            }
        }
        val addCar: AddCarRoomUseCase = AddCarRoomUseCase()
        GlobalScope.launch {
            addCar.addCarToRoom(
                CarLocal(
                    brand ="bmw",
                    model ="série 3",
                    releaseDate = Date().toString(),
                    fuel ="essence",
                    transmission ="3",
                    motorization = "W16",
                    power = 125,
                    torque = 340,
                    maxSpeed = 200,
                    mileage = listOf(0),
                    ownerID = 1
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
                        cacheManagerRepository.saveUserCarList(result.data!!)
                    }

                    else -> {}

                }


        }


    }
}


companion object {

}
}