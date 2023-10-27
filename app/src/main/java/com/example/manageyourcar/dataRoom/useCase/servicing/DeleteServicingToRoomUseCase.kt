package com.example.manageyourcar.dataRoom.useCase.servicing

import com.example.manageyourcar.dataRoom.repository.ServicingRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteServicingToRoomUseCase: KoinComponent {
    val roomRepository by inject<ServicingRepository>()

    suspend fun deleteServicingToRoom(idServicing: Int){
        roomRepository.deleteServicing(idServicing);
    }
}