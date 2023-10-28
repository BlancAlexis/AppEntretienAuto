package com.example.manageyourcar.domainLayer.useCaseRoom.servicing

import com.example.manageyourcar.dataLayer.dataLayerRoom.repository.ServicingRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteServicingToRoomUseCase : KoinComponent {
    val roomRepository by inject<ServicingRepository>()

    suspend fun deleteServicingToRoom(idServicing: Int) {
        roomRepository.deleteServicing(idServicing)
    }
}