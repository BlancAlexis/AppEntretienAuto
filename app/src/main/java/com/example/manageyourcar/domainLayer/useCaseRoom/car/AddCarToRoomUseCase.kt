package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.dataLayer.CacheManagerRepositoryImpl
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import com.example.manageyourcar.dataLayer.model.Car
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddCarToRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()
    val cacheManagerRepository by inject<CacheManagerRepositoryImpl>()

    suspend fun addCarToRoom(car : Car) {
        when (val result= cacheManagerRepository.getUserId(AppApplication.instance)){
            is Ressource.Error -> TODO()
            is Ressource.Loading -> TODO()
            is Ressource.Success -> {
                car.copy(
                    ownerID = result.data
                )
             roomRepository.addNewCar(car)
            }
        }
    }
}