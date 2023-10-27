package com.example.manageyourcar.dataRoom.useCase.servicing

import com.example.manageyourcar.dataRoom.model.Servicing
import com.example.manageyourcar.dataRoom.repository.ServicingRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddServicingToRoomUseCase: KoinComponent {
    val roomRepository by inject<ServicingRepository>()

    suspend fun addServicingFromRoom(id: Int, name: String, iterationKilometre: Int) {
        return roomRepository.addNewServicing(Servicing(id, name, iterationKilometre));
    }
}