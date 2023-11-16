package com.example.manageyourcar.domainLayer.repository.room

import com.example.manageyourcar.dataLayer.model.Car

interface CarRepository {
    fun addNewCar(car: Car)
    fun getCars(idUser : Int) : List<Car>
    fun getCars(): List<Car>
    fun getCar(idCar: Int): Car
    fun updateCar(car: Car)
    fun deleteCar(idCar: Int)
}