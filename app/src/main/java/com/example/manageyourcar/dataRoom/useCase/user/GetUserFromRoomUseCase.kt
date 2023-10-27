package com.example.manageyourcar.dataRoom.useCase.user

import com.example.manageyourcar.dataRoom.model.User
import com.example.manageyourcar.dataRoom.repository.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUserFromRoomUseCase : KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun getUserFromRoom(idUser: Int): User{
        return roomRepository.getUser(idUser);
    }
}