package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteCarToRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun deleteCarToRoom(idCar: Int) {
        roomRepository.deleteCar(idCar)
    }
}