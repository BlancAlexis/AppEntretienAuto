package com.example.manageyourcar.dataLayer.repository.room.car

import com.example.manageyourcar.dataLayer.model.dataClass.room.Car
import com.example.manageyourcar.dataLayer.util.Ressource
import kotlinx.coroutines.flow.Flow

interface CarRepository {
     fun addNewCar(car: Car)
     //fun getCar(): Flow<List<Car>>
     fun updateCar(car: Car)
     fun deleteCar(idCar: Long)
}