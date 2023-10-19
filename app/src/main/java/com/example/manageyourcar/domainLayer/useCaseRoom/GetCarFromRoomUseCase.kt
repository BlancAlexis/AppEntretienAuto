package com.example.manageyourcar.domainLayer.useCaseRoom

import com.example.manageyourcar.domain.repository.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCarFromRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun getCarFromRoom() {
      //  roomRepository.getCar()
    }
}