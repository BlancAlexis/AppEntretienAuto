package com.example.manageyourcar.dataLayer.service.room.car

import com.example.manageyourcar.dataLayer.dataSource.room.CarDao
import com.example.manageyourcar.dataLayer.dataSource.room.CarEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarDatabaseServiceImpl @Inject constructor(
    private val carDao: CarDao
): CarDatabaseService {
    override fun addNewCar(carEntity: CarEntity) = carDao.addNewCar(carEntity)

    override fun getCar(): Flow<List<CarEntity>> = carDao.getCar()

    override fun updateCar(carEntity: CarEntity) = carDao.updateCar(carEntity)

    override fun deleteCar(idCar: Long) = carDao.deleteCar(idCar)
}