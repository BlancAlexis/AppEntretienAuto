package com.example.manageyourcar.domainLayer.useCaseBusiness

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginUserUseCase : KoinComponent {
    private val user by inject<UserRepository>()
    private val cacheManager by inject<CacheManagerRepository>()
    suspend fun invoke(login: String, password: String): Ressource<Int> {
        return try {
            when (val resultWhen = cacheManager.getUserId()) {
                is Ressource.Success -> Ressource.Success(resultWhen.data)
                is Ressource.Error -> {
                    val result = user.logUser(login, password)
                    if (result.login == login && result.password == password) {
                        Ressource.Success(result.uuid)
                    } else {
                        Ressource.Error(message = "Identifiant non valide")
                    }
                }

                else -> {
                    Ressource.Error(resultWhen.error)
                }
            }
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }
}