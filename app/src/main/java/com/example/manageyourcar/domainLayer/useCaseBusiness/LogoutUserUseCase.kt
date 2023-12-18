package com.example.manageyourcar.domainLayer.useCaseBusiness

import android.content.Context
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class LogoutUserUseCase : KoinComponent {
    private val cacheManager by inject<CacheManagerRepository>()
    suspend fun logoutUser(context : Context): Ressource<Boolean> {
        return try {
            when (val resultWhen = cacheManager.resetUserId(context)) {
                is Ressource.Success -> Ressource.Success(resultWhen.data)
                is Ressource.Error -> Ressource.Error(message = resultWhen.message)
                else -> {
                    Ressource.Error(resultWhen.error)
                }
            }
        }catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}