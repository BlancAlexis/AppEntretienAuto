package com.example.manageyourcar.domainLayer.repository

import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource

interface CacheManagerRepository {
    suspend fun getUserCarList(): Ressource<List<Car>>
    suspend fun saveUserCarList(local: List<Car>)
    suspend fun putUserId(userId: Int): Ressource<Boolean>
    suspend fun getUserId(): Ressource<Int>
    suspend fun resetUserId(): Ressource<Boolean>
}