package com.example.manageyourcar.dataRoom.useCase.entretien

import com.example.manageyourcar.dataRoom.repository.EntretienRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteEntretienToRoomUseCase: KoinComponent {
    val roomRepository by inject<EntretienRepository>()

    suspend fun deleteEntretienToRoom(idEntretien: Int){
        roomRepository.deleteEntretien(idEntretien);
    }
}