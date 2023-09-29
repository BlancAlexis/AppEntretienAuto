package com.example.manageyourcar.model

import com.example.manageyourcar.model.dataClass.Car
import kotlinx.coroutines.flow.Flow

interface MyRepository {
    suspend fun getVehiculeBySiv(siv : String): Flow<Ressource<Car>>
    suspend fun getVehiculeByImmat(immat : String) : Flow<Ressource<Car>>

}