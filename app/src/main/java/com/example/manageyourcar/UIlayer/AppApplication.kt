package com.example.manageyourcar.UIlayer

import android.app.Application
import android.util.Log
import com.example.manageyourcar.dataLayer.dataLayerFirebase.CarFirestoreDataSource
import com.example.manageyourcar.dataLayer.dataLayerFirebase.MaintenanceFirestoreRepository
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.di.injectFeature
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.dataLayer.model.MaintenanceService
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.useCaseRoom.car.StoreUserCarUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.DeleteCarRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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

    /*    val deleteCarRoomUseCase = DeleteCarRoomUseCase()
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
                        val dataSource = get<CarFirestoreDataSource> ()
                       // dataSource.deleteCar(result.data.last())
                        dataSource.updateCar(result.data.last().copy(mileage = listOf(0,10224)))
                    }
                }

                else -> {}
            }
        }
        GlobalScope.launch(Dispatchers.IO) {
            val dataSource = get<MaintenanceFirestoreRepository>()
            dataSource.addNewServicing(
                Entretien(
                    date = Date(),
                    mileage = 12332,
                    service = MaintenanceService.Freins(),
                    price = 1223
                )
            )
        }
        val addCar: StoreUserCarUseCase = StoreUserCarUseCase()
        GlobalScope.launch {
            addCar.invoke(
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

        }*/
        println("prout")
        val getCarRoomUseCase = GetUserCarsUseCase()
        GlobalScope.launch {
        /*    getCarRoomUseCase.invoke().collect { result ->
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


        }*/


    }
}


companion object {

}
}