package com.example.manageyourcar.dataRoom.repository

import com.example.manageyourcar.dataRoom.model.Car

interface CarRepository {
     fun addNewCar(car: Car)
     fun getCar(): List<Car>
     fun updateCar(car: Car)
     fun deleteCar(idCar: Long)
}