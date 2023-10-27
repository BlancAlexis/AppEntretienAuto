package com.example.manageyourcar.dataRoom.useCase.car

import com.example.manageyourcar.dataRoom.model.Car
import com.example.manageyourcar.dataRoom.repository.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateCarToRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun updateCarToRoom(car: Car) {
        roomRepository.updateCar(car);
    }
}