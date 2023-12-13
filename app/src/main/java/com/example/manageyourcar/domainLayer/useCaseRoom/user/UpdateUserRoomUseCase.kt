package com.example.manageyourcar.domainLayer.useCaseRoom.user

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.User
import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateUserRoomUseCase : KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun getUpdateFromRoom(user: User) : Ressource<Unit> {
        return try {
            roomRepository.updateUser(user)
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }
}