package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddCarToRoomUseCase : KoinComponent{
    private val roomRepository by inject<CarRepository>()
    private val cacheManagerRepository by inject<CacheManagerRepository>()

    suspend fun addCarToRoom(car: Car): Ressource<Unit> {
        return try {
            when (val result = cacheManagerRepository.getUserId(AppApplication.instance)) {
                is Ressource.Error -> Ressource.Error(result.error)
                is Ressource.Loading -> Ressource.Error()
                is Ressource.Success -> {
                    val carWithOwnerID : Car = car.copy(
                        ownerID = result.data
                    )
                    roomRepository.addNewCar(carWithOwnerID)
                    Ressource.Success(Unit)
                }
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
            Ressource.Error(exception = e)
        }
    }
}