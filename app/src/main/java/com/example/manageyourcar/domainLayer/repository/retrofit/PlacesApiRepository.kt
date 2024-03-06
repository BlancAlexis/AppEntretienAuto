package com.example.manageyourcar.domainLayer.repository.retrofit

import com.example.manageyourcar.dataLayer.retrofit.model.Place
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import kotlinx.coroutines.flow.Flow

interface PlacesApiRepository {
    suspend fun getPlacesBy(
        latitude: Double,
        longitude: Double,
    ): Flow<Ressource<List<Place>>>
}