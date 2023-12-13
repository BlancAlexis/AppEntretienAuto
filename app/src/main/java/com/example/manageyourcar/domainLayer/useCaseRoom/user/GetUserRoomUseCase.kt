package com.example.manageyourcar.domainLayer.useCaseRoom.user

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.User
import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUserRoomUseCase : KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun getUserFromRoom(idUser: Int): Ressource<User> {
        return try {
         val user= roomRepository.getUser(idUser)
        Ressource.Success(user)
        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }
}