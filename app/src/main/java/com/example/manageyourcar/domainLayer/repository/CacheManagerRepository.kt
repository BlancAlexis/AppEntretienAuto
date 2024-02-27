package com.example.manageyourcar.domainLayer.repository

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal

interface CacheManagerRepository {
    suspend fun getUserCarList(): Ressource<List<CarLocal>>
    suspend fun saveUserCarList(local: List<CarLocal>)
}