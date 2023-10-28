package com.example.manageyourcar.domainLayer.useCaseRoom.servicing

import com.example.manageyourcar.dataLayer.dataLayerRoom.repository.ServicingRepository
import com.example.manageyourcar.dataLayer.model.Servicing
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddServicingToRoomUseCase : KoinComponent {
    val roomRepository by inject<ServicingRepository>()

    suspend fun addServicingFromRoom(id: Int, name: String, iterationKilometre: Int) {
        return roomRepository.addNewServicing(Servicing(id, name, iterationKilometre))
    }
}