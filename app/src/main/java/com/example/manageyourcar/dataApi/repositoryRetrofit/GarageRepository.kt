package com.example.manageyourcar.dataApi.repositoryRetrofit

import com.example.manageyourcar.dataApi.model.dataClass.Car
import com.example.manageyourcar.dataApi.model.dataClass.Place
import com.example.manageyourcar.dataApi.util.Ressource
import kotlinx.coroutines.flow.Flow

interface GarageRepository {
        suspend fun getPlacesBy(
            latitude: Double,
            longitude: Double,
        ): Flow<Ressource<List<Place>>>
}