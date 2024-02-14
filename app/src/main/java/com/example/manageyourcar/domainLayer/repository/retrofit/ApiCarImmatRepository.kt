package com.example.manageyourcar.domainLayer.repository.retrofit

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import kotlinx.coroutines.flow.Flow

interface ApiCarImmatRepository {
    suspend fun getVehiculeByImmat(immat: String): Flow<Ressource<CarLocal>>

}