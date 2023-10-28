package com.example.manageyourcar.domainLayer.repository.retrofit

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.model.Place
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import kotlinx.coroutines.flow.Flow

interface GarageRepository {
    suspend fun getPlacesBy(
        latitude: Double,
        longitude: Double,
    ): Flow<Ressource<List<Place>>>
}