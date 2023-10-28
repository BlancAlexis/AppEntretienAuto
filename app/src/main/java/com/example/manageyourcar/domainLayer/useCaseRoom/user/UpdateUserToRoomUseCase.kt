package com.example.manageyourcar.domainLayer.useCaseRoom.user

import com.example.manageyourcar.dataLayer.dataLayerRoom.repository.UserRepository
import com.example.manageyourcar.dataLayer.model.User
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateUserToRoomUseCase : KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun getUpdateFromRoom(user: User) {
        roomRepository.updateUser(user)
    }
}