package com.example.manageyourcar.domainLayer.useCaseRoom.servicing

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.repository.room.ServicingRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddCarMaintenanceUseCase : KoinComponent {
    private val roomRepository by inject<ServicingRepository>()
    private val cacheManagerRepository by inject<CacheManagerRepository>()

    suspend fun addMaintenanceOperation(entretien: Entretien): Ressource<Unit> {
        return try {
            when (val result = cacheManagerRepository.getUserId()) {
                is Ressource.Error -> Ressource.Error(result.error)
                is Ressource.Loading -> Ressource.Error()
                is Ressource.Success -> {
                    val maintenanceWithUserID: Entretien = entretien.copy(
                        userID = result.data
                    )
                    roomRepository.addNewServicing(maintenanceWithUserID)
                    Ressource.Success(Unit)
                }
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
            Ressource.Error(exception = e)
        }
    }
}