package com.example.manageyourcar.domainLayer.repository

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal

interface CacheManagerRepository {
    suspend fun getUserCarList(): Ressource<List<CarLocal>>
    suspend fun saveUserCarList(local: List<CarLocal>)
    suspend fun putUserId(userId: Int): Ressource<Boolean>
    suspend fun getUserId(): Ressource<Int>
    suspend fun resetUserId(): Ressource<Boolean>
}