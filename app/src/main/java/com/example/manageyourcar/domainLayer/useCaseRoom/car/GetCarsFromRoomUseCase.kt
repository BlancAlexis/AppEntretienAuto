package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.dataLayer.dataLayerRoom.repository.CarRepository
import com.example.manageyourcar.dataLayer.model.Car
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCarsFromRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun getCarsFromRoom(): List<Car> {
        return roomRepository.getCars()
    }
}