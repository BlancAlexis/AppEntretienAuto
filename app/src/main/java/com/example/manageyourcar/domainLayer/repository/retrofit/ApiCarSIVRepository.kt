package com.example.manageyourcar.domainLayer.repository.retrofit

import com.example.manageyourcar.dataLayer.retrofit.model.CarRetrofit
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import kotlinx.coroutines.flow.Flow

interface ApiCarSIVRepository {
    suspend fun getVehiculeBySiv(siv: String): Flow<Ressource<CarRetrofit>>

}