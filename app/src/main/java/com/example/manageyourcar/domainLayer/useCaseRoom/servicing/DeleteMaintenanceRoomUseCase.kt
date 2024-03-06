package com.example.manageyourcar.domainLayer.useCaseRoom.servicing

import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.room.ServicingRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteMaintenanceRoomUseCase : KoinComponent {
    val roomRepository by inject<ServicingRepository>()

    suspend fun deleteServicingToRoom(idServicing: Int): Ressource<Unit> {
        return try {
            roomRepository.deleteServicing(idServicing)
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }
}