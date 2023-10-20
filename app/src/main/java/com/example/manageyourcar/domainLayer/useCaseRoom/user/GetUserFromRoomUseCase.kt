package com.example.manageyourcar.domainLayer.useCaseRoom.user

import com.example.manageyourcar.dataRoom.model.User
import com.example.manageyourcar.dataRoom.repository.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUserFromRoomUseCase: KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun getUserFromRoom(): List<User> {
        return roomRepository.getUsers();
    }
}