package com.example.manageyourcar.domainLayer.useCaseRoom.user

import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import com.example.manageyourcar.dataLayer.model.User
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddUserToRoomUseCase : KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun addUserToRoom(
        id: Int,
        login: String,
        password: String,
        firstname: String,
        lastname: String
    ) {
        roomRepository.addNewUser(User(id, login, password, firstname, lastname))
    }
}