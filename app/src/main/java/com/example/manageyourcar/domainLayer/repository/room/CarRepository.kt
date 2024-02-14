package com.example.manageyourcar.domainLayer.repository.room

import com.example.manageyourcar.dataLayer.model.CarLocal
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    fun addNewCar(carLocal: CarLocal)
    fun getCars(idUser: Int): Flow<List<CarLocal>>
    fun getCars(): List<CarLocal>
    fun getCar(idCar: Int): CarLocal
    fun updateCar(carLocal: CarLocal)
    fun updateCarMileage(listMileages: List<Int>, idCar: Int)
    fun deleteCar(idCar: Int)
}