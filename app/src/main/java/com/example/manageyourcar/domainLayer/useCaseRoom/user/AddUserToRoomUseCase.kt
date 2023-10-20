package com.example.manageyourcar.domainLayer.useCaseRoom.user

import com.example.manageyourcar.dataRoom.model.Car
import com.example.manageyourcar.dataRoom.model.User
import com.example.manageyourcar.dataRoom.repository.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddUserToRoomUseCase: KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun addCarToRoom(i: Int, s: String, s1: String) {
        roomRepository.addNewUser(User(0,"juju","FlutterBetter"))
    }
}