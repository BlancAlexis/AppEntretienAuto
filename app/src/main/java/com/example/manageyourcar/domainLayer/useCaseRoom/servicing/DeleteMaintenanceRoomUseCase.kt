package com.example.manageyourcar.domainLayer.useCaseRoom.servicing

import com.example.manageyourcar.dataLayer.dataLayerFirebase.MaintenanceFirestoreRepository
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteMaintenanceRoomUseCase : KoinComponent {
    val roomRepository by inject<MaintenanceFirestoreRepository>()

    suspend operator fun invoke(idServicing: Int): Ressource<Unit> {
        return try {
            roomRepository.deleteServicing(idServicing)
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }
}