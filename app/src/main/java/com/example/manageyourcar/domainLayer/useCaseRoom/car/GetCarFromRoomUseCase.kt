package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.dataLayer.dataLayerRoom.repository.CarRepository
import com.example.manageyourcar.dataLayer.model.Car
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCarFromRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun getCarFromRoom(idCar: Int): Car {
        return roomRepository.getCar(idCar)
    }
}