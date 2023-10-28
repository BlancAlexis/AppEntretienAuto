package com.example.manageyourcar.domainLayer.repository.retrofit

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.model.Car
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import kotlinx.coroutines.flow.Flow

interface ApiCarSIVRepository {
    suspend fun getVehiculeBySiv(siv: String): Flow<Ressource<Car>>

}