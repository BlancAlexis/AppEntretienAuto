package com.example.manageyourcar.domainLayer.useCaseRoom.car

import android.util.Log
import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddCarRoomUseCase : KoinComponent {
    private val roomRepository by inject<CarRepository>()
    private val cacheManagerRepository by inject<CacheManagerRepository>()

    suspend fun addCarToRoom(carLocal: CarLocal): Ressource<Unit> {
        return try {
            when (val result = cacheManagerRepository.getUserId(AppApplication.instance)) {
                is Ressource.Error -> Ressource.Error(result.error)
                is Ressource.Loading -> Ressource.Error()
                is Ressource.Success -> {
                    val carLocalWithOwnerID: CarLocal = carLocal.copy(
                        ownerID = result.data,
                        mileage = listOf(0)
                    )
                    roomRepository.addNewCar(carLocalWithOwnerID)
                    Ressource.Success(Unit)
                }
            }
        } catch (e: Exception) {
            Log.e("AddCarRoomUseCase",e.localizedMessage)
            Ressource.Error(exception = e)
        }
    }
}