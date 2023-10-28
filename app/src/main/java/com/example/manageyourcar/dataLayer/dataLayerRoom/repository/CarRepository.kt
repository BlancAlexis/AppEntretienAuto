package com.example.manageyourcar.dataLayer.dataLayerRoom.repository

import com.example.manageyourcar.dataLayer.model.Car

interface CarRepository {
    fun addNewCar(car: Car)
    fun getCars(): List<Car>
    fun getCar(idCar: Int): Car
    fun updateCar(car: Car)
    fun deleteCar(idCar: Int)
}