package com.example.manageyourcar.domainLayer.useCaseBusiness

import com.example.manageyourcar.dataLayer.AuthRepository
import com.example.manageyourcar.dataLayer.dataLayerFirebase.UserRepository
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.User
import kotlinx.coroutines.flow.flowOf

class creaUserUseCase(
    private val authRepository: AuthRepository, private val userRepository: UserRepository
) {

    suspend fun invoke(
        email: String, password: String, firstname: String, lastname: String
    ): Ressource<Unit> {
        return try {
            authRepository.createUser(email, password).collect {
                when (it) {
                    is Ressource.Success -> {
                        when (val result = userRepository.addNewUser(User(uuid = it.data.uid, firstname = firstname, lastname = lastname, login = email))) {
                            is Ressource.Success -> Ressource.Success(Unit)
                            is Ressource.Error -> Ressource.Error(result.error)
                            else -> {Ressource.Success(Unit)}
                        }
                    }
                    is Ressource.Error -> {
                        Ressource.Error(it.error)
                    }
                    else -> {Ressource.Success(Unit)}
                }
            }
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }
}