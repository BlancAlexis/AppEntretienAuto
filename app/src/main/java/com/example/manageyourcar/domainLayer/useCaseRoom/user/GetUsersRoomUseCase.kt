package com.example.manageyourcar.domainLayer.useCaseRoom.user

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.User
import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUsersRoomUseCase : KoinComponent {
    val roomRepository by inject<UserRepository>()

    suspend fun getUsersFromRoom(): Ressource<List<User>> {
        return try {
            val users = roomRepository.getUsers()
            Ressource.Success(users)
        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }
}