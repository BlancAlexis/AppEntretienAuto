package com.example.manageyourcar.domain.repository

import com.example.manageyourcar.domain.model.Car

interface CarRepository {
     fun addNewCar(car: Car)
     //fun getCar(): Flow<List<Car>>
     fun updateCar(car: Car)
     fun deleteCar(idCar: Long)
}