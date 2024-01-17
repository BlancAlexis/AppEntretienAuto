package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteCarRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun deleteCar(car: Car): Ressource<Int> {
        return try {
            roomRepository.deleteCar(car)
            Ressource.Success(car.carID)
        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }
}