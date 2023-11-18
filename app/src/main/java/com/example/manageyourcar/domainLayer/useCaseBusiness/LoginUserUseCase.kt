package com.example.manageyourcar.domainLayer.useCaseBusiness

import android.content.Context
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginUserUseCase : KoinComponent {
    private val user by inject<UserRepository>()
    private val cacheManager by inject<CacheManagerRepository>()
    suspend fun loginUser(login: String, password: String, context: Context): Ressource<Int> {
        return try {
            when (val resultWhen = cacheManager.getUserId(context)) {
                is Ressource.Success -> Ressource.Success(resultWhen.data)
                is Ressource.Error -> {
                    val result = user.logUser(login, password)
                    if (result.login == login && result.password == password) {
                        Ressource.Success(result.id)
                    } else {
                        Ressource.Error(message = "Identifiant non valide")
                    }
                }
                else -> {
                    Ressource.Error(resultWhen.error)
                }
            }
        }catch (e: Exception) {
            Ressource.Error(e)
        }
    }
}