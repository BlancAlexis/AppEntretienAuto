package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateCarRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun updateCarToRoom(car: Car): Ressource<Unit> {
        return try {
            roomRepository.updateCar(car)
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }
}