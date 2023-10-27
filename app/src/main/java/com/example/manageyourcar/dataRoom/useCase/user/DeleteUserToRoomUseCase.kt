package com.example.manageyourcar.dataRoom.useCase.user

import com.example.manageyourcar.dataRoom.repository.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteUserToRoomUseCase: KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun deleteUserFromRoom(idUser: Int){
        roomRepository.deleteUser(idUser);
    }
}