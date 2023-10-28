package com.example.manageyourcar.domainLayer.useCaseRoom.servicing

import com.example.manageyourcar.domainLayer.repository.room.ServicingRepository
import com.example.manageyourcar.dataLayer.model.Servicing
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateServicingToRoomUseCase : KoinComponent {
    val roomRepository by inject<ServicingRepository>()

    suspend fun updateServicingToRoom(servicing: Servicing) {
        roomRepository.updateServicing(servicing)
    }
}