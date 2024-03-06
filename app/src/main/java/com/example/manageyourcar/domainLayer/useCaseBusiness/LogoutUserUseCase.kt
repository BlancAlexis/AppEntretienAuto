package com.example.manageyourcar.domainLayer.useCaseBusiness

import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LogoutUserUseCase : KoinComponent {
    private val cacheManager by inject<CacheManagerRepository>()
    suspend fun logoutUser(): Ressource<Boolean> {
        return try {
            when (val resultWhen = cacheManager.resetUserId()) {
                is Ressource.Success -> Ressource.Success(resultWhen.data)
                is Ressource.Error -> Ressource.Error(message = resultWhen.message)
                else -> {
                    Ressource.Error(resultWhen.error)
                }
            }
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}