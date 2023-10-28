package com.example.manageyourcar.domainLayer.useCaseRoom.user

import com.example.manageyourcar.dataLayer.dataLayerRoom.repository.UserRepository
import com.example.manageyourcar.dataLayer.model.User
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUsersFromRoomUseCase : KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun getUsersFromRoom(): List<User> {
        return roomRepository.getUsers()
    }
}