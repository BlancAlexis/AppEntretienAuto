package com.example.manageyourcar.model

import kotlinx.coroutines.flow.Flow

interface MyRepository {
    suspend fun getVehiculeBySiv(siv : String): Flow<Ressource<Car>>

}