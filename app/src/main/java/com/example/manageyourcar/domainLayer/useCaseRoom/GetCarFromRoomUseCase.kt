package com.example.manageyourcar.domainLayer.useCaseRoom

import com.example.manageyourcar.dataRoom.model.Car
import com.example.manageyourcar.dataRoom.repository.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCarFromRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun getCarFromRoom(): List<Car> {
        return roomRepository.getCar();
    }
}