package com.example.manageyourcar.domainLayer.useCaseRoom.user

import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import com.example.manageyourcar.dataLayer.model.User
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUserFromRoomUseCase : KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun getUserFromRoom(idUser: Int): User {
        return roomRepository.getUser(idUser)
    }
}