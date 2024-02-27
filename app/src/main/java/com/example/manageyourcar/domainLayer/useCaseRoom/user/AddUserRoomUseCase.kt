package com.example.manageyourcar.domainLayer.useCaseRoom.user

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.User
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddUserRoomUseCase : KoinComponent {
/*    val roomRepository by inject<UserRepository>()

    suspend fun invoke(login: String, password: String, firstname: String, lastname: String
    ): Ressource<Unit> {
        return try {
            roomRepository.addNewUser(User(login = login, password = password, firstname = firstname, lastname = lastname))
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }*/
}