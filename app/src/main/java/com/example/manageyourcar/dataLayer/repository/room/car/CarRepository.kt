package com.example.manageyourcar.dataLayer.repository.room.car

import com.example.manageyourcar.dataLayer.model.dataClass.room.Car
import com.example.manageyourcar.dataLayer.util.Ressource
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    suspend fun addNewCar(car: Car)
    suspend fun getCar(): Flow<List<Car>>
    suspend fun updateCar(car: Car)
    suspend fun deleteCar(idCar: Long)
}