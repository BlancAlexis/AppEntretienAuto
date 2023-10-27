package com.example.manageyourcar.dataRoom.useCase.user

import com.example.manageyourcar.dataRoom.model.User
import com.example.manageyourcar.dataRoom.repository.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateUserToRoomUseCase: KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun getUpdateFromRoom(user: User){
        roomRepository.updateUser(user);
    }
}