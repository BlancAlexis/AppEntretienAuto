package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpsertCarMileageUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun updateCarMileage(car: Car): Ressource<Unit> {
        return try {
            roomRepository.updateCarMileage(car.mileage, car.carID ?: 1)
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }
}