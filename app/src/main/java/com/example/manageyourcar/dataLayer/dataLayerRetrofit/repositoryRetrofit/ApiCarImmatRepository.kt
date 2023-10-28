package com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryRetrofit

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.model.Car
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import kotlinx.coroutines.flow.Flow

interface ApiCarImmatRepository {
    suspend fun getVehiculeByImmat(immat: String): Flow<Ressource<Car>>

}