package com.example.manageyourcar.domainLayer.useCaseRoom.user

import android.util.Log
import com.example.manageyourcar.dataRoom.model.User
import com.example.manageyourcar.dataRoom.repository.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddUserToRoomUseCase: KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun addUserToRoom(i: Int, s: String, s1: String) {
        roomRepository.addNewUser(User(i,s,s1))
    }
}