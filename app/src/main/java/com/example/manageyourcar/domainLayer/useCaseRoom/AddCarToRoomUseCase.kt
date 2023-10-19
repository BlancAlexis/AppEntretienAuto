package com.example.manageyourcar.domainLayer.useCaseRoom

import com.example.manageyourcar.dataRoom.model.Car
import com.example.manageyourcar.dataRoom.repository.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddCarToRoomUseCase : KoinComponent{
    val roomRepository by inject<CarRepository>()

    suspend fun addCarToRoom(i: Int, s: String, s1: String) {
        roomRepository.addNewCar(Car(0,"f","d"))
    }
}