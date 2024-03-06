package com.example.manageyourcar.domainLayer.useCaseRoom.car

import android.util.Log
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddCarRoomUseCase : KoinComponent {
    private val roomRepository by inject<CarRepository>()
    private val cacheManagerRepository by inject<CacheManagerRepository>()

    suspend fun addCarToRoom(car: Car): Ressource<Unit> {
        return try {
            when (val result = cacheManagerRepository.getUserId()) {
                is Ressource.Error -> Ressource.Error(result.error)
                is Ressource.Loading -> Ressource.Error()
                is Ressource.Success -> {
                    val carWithOwnerID: Car = car.copy(
                        ownerID = result.data,
                        mileage = listOf(0)
                    )
                    roomRepository.addNewCar(carWithOwnerID)
                    Ressource.Success(Unit)
                }
            }
        } catch (e: Exception) {
            Log.e("AddCarRoomUseCase", e.localizedMessage)
            Ressource.Error(exception = e)
        }
    }
}