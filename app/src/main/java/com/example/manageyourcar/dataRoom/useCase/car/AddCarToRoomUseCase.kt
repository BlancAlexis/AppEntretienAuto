package com.example.manageyourcar.dataRoom.useCase.car

import com.example.manageyourcar.dataRoom.model.Car
import com.example.manageyourcar.dataRoom.repository.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Date

class AddCarToRoomUseCase : KoinComponent{
    val roomRepository by inject<CarRepository>()

    suspend fun addCarToRoom(car: Car) {
        roomRepository.addNewCar(car)
    }
}