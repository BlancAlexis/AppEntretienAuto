package com.example.manageyourcar.dataRoom.useCase.servicing

import com.example.manageyourcar.dataRoom.model.Servicing
import com.example.manageyourcar.dataRoom.repository.ServicingRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetServicingFromRoomUseCase : KoinComponent {
    val roomRepository by inject<ServicingRepository>()

    suspend fun getServicingFromRoom(idServicing: Int): Servicing{
        return roomRepository.getServicing(idServicing);
    }
}