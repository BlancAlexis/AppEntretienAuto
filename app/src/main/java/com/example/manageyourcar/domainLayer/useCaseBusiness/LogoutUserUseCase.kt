package com.example.manageyourcar.domainLayer.useCaseBusiness

import com.example.manageyourcar.dataLayer.AuthRepository
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LogoutUserUseCase(    private val authRepository: AuthRepository
){
    suspend fun invoke(): Ressource<Unit> {
        return try {
    authRepository.logoutUser()
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}