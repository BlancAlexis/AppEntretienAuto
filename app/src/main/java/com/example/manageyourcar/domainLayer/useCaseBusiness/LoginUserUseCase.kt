package com.example.manageyourcar.domainLayer.useCaseBusiness

import android.app.Activity
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginUserUseCase : KoinComponent {
    private val user by inject<UserRepository>()
    private val cacheManager by inject<CacheManagerRepository>()
    suspend fun loginUser(login: String, password: String, activity: Activity): Ressource<Boolean> {
        return try {
            if(cacheManager.getUserId(activity).data!=null){
                Ressource.Success(true)}
            val result = user.logUser(login, password)
            if (result.login == login && result.password == password) {
                Ressource.Success(true)
            } else {
                Ressource.Success(false)
            }
        } catch (e: Exception) {
            Ressource.Error(e)
      }
    }
}