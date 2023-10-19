package com.example.manageyourcar.dataApi.repositoryRetrofit

import com.example.manageyourcar.dataApi.model.dataClass.Car
import com.example.manageyourcar.dataApi.util.Ressource
import kotlinx.coroutines.flow.Flow

interface ApiCarImmatRepository {
    suspend fun getVehiculeByImmat(immat : String) : Flow<Ressource<Car>>

}