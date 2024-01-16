package com.example.manageyourcar.domainLayer.useCaseRoom.user

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteUserRoomUseCase : KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun deleteUserFromRoom(idUser: Int): Ressource<Unit> {
        return try {
            roomRepository.deleteUser(idUser)
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }
}