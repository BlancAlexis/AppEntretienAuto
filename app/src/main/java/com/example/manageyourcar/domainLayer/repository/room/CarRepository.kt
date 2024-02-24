package com.example.manageyourcar.domainLayer.repository.room

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.CarEntity
import com.example.manageyourcar.dataLayer.model.CarLocal
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    fun addNewCar(carLocal: CarLocal) : Ressource<Unit>
    fun getCars(idUser: Int): Flow<Ressource<List<CarLocal>>>
    fun getCars(): List<CarLocal>
    fun getCar(idCar: Int): Flow<Ressource<CarLocal>>
    fun updateCar(carLocal: CarLocal) : Flow<Ressource<Unit>>
    fun updateCarMileage(listMileages: List<Int>, idCar: Int) :  Flow<Ressource<Unit>>
    fun deleteCar(car: CarLocal) : Flow<Ressource<Unit>>
}