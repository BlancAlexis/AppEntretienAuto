package com.example.manageyourcar.domainLayer.useCaseBusiness

import com.example.manageyourcar.dataLayer.AuthRepository
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginUserUseCase : KoinComponent {
    private val user by inject<AuthRepository>()
    suspend fun invoke(login: String, password: String): Flow<Ressource<FirebaseUser>> {
        return try {
            user.loginUser(login, password)
        } catch (e: Exception) {
            flowOf( Ressource.Error(e) )
        }
    }
}