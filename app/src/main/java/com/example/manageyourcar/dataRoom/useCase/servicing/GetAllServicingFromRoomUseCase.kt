package com.example.manageyourcar.dataRoom.useCase.servicing

import com.example.manageyourcar.dataRoom.model.Servicing
import com.example.manageyourcar.dataRoom.repository.ServicingRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetAllServicingFromRoomUseCase : KoinComponent {
    val roomRepository by inject<ServicingRepository>()

    suspend fun getAllServicingFromRoom(): List<Servicing>{
        return roomRepository.getAllServicing();
    }
}