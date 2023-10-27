package com.example.manageyourcar.dataRoom.useCase.entretien

import com.example.manageyourcar.dataRoom.model.Entretien
import com.example.manageyourcar.dataRoom.repository.EntretienRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddEntretienToRoomUseCase: KoinComponent {
    val roomRepository by inject<EntretienRepository>()

    suspend fun addEntretienFromRoom(entretien: Entretien) {
        return roomRepository.addNewEntretien(entretien);
    }
}