package com.example.manageyourcar.dataRoom.useCase.user

import com.example.manageyourcar.dataRoom.model.User
import com.example.manageyourcar.dataRoom.repository.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddUserToRoomUseCase: KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun addUserToRoom(id: Int, login: String, password: String, firstname: String, lastname: String) {
        roomRepository.addNewUser(User(id,login,password,firstname, lastname))
    }
}