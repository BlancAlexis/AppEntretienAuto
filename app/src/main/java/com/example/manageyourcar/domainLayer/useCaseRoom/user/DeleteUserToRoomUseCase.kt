package com.example.manageyourcar.domainLayer.useCaseRoom.user

import com.example.manageyourcar.dataLayer.dataLayerRoom.repository.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteUserToRoomUseCase : KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun deleteUserFromRoom(idUser: Int) {
        roomRepository.deleteUser(idUser)
    }
}