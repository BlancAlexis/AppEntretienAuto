package com.example.manageyourcar.domainLayer.useCaseBusiness

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.useCaseRoom.user.GetUserFromRoomUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginUserUseCase : KoinComponent {
    private val user by inject<GetUserFromRoomUseCase>()
    fun loginUser(email: String, password: String): Ressource<Boolean> {
       return try {
            val user = user.getUserFromRoom(1)
            if (user.email == email && user.password == password) {
                Ressource.Success(true)
            } else {
                Ressource.Success(false)
            }
        } catch (e: Exception) {
            Ressource.Error(e)
      }
    }
}