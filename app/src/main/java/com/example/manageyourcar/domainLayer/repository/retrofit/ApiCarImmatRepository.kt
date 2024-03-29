package com.example.manageyourcar.domainLayer.repository.retrofit

import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import kotlinx.coroutines.flow.Flow

interface ApiCarImmatRepository {
    suspend fun getVehiculeByImmat(immat: String): Flow<Ressource<Car>>

}