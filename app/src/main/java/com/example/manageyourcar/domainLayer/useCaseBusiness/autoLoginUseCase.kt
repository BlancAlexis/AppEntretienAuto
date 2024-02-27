package com.example.manageyourcar.domainLayer.useCaseBusiness

import com.example.manageyourcar.dataLayer.AuthRepository
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.google.firebase.auth.FirebaseUser

class autoLoginUseCase(
    private val authRepository: AuthRepository
) {
    fun invoke() : Ressource<FirebaseUser> {
        return authRepository.autoLogUser()
    }
}