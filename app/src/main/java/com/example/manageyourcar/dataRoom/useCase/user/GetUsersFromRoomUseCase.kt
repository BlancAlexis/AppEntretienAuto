package com.example.manageyourcar.dataRoom.useCase.user

import com.example.manageyourcar.dataRoom.model.User
import com.example.manageyourcar.dataRoom.repository.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUsersFromRoomUseCase: KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun getUsersFromRoom(): List<User> {
        return roomRepository.getUsers();
    }
}