package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCarsFromRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun getCarsFromRoom(): List<Car> {
        return roomRepository.getCars()
    }
}