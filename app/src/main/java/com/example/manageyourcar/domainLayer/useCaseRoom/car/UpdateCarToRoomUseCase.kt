package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.dataLayer.dataLayerRoom.repository.CarRepository
import com.example.manageyourcar.dataLayer.model.Car
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateCarToRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun updateCarToRoom(car: Car) {
        roomRepository.updateCar(car)
    }
}