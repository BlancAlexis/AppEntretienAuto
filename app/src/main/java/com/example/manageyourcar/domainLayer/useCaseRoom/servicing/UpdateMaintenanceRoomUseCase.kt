package com.example.manageyourcar.domainLayer.useCaseRoom.servicing

import com.example.manageyourcar.domainLayer.repository.room.ServicingRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateMaintenanceRoomUseCase : KoinComponent {
    val roomRepository by inject<ServicingRepository>()

   /* suspend fun updateServicingToRoom(servicing: Servicing) {
        roomRepository.updateServicing(servicing)
    }*/
}