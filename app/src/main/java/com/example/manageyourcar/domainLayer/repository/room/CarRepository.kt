package com.example.manageyourcar.domainLayer.repository.room

import com.example.manageyourcar.dataLayer.model.Car
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    fun addNewCar(car: Car)
    fun getCars(idUser : Int) : Flow<List<Car>>
    fun getCars(): List<Car>
    fun getCar(idCar: Int): Car
    fun updateCar(car: Car)
    fun updateCarMileage(ListMileage: List<Int>, idCar: Int)
    fun deleteCar(idCar: Int)
}