package com.example.manageyourcar.dataLayer.repository

import com.example.manageyourcar.dataLayer.model.dataClass.Car
import com.example.manageyourcar.dataLayer.util.Ressource
import kotlinx.coroutines.flow.Flow

interface ApiCarImmatRepository {
    suspend fun getVehiculeByImmat(immat : String) : Flow<Ressource<Car>>

}