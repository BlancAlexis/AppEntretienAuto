package com.example.manageyourcar.dataLayer.service.room.car

import com.example.manageyourcar.dataLayer.dataSource.room.CarEntity
import kotlinx.coroutines.flow.Flow

interface CarDatabaseService {
    fun addNewCar(contactEntity: CarEntity)
    fun getCar(): Flow<List<CarEntity>>
    fun updateCar(contactEntity: CarEntity)
    fun deleteCar(idContact: Long)
}