package com.example.manageyourcar.dataLayer.service.room.car

import com.example.manageyourcar.dataLayer.dataSource.room.CarDao
import com.example.manageyourcar.dataLayer.dataSource.room.CarEntity
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CarDatabaseServiceImpl : CarDatabaseService, KoinComponent {
   val carDao by inject<CarDao>()
    override fun addNewCar(carEntity: CarEntity) = carDao.addNewCar(carEntity)

    override fun getCar(): Flow<List<CarEntity>> = carDao.getCar()

    override fun updateCar(carEntity: CarEntity) = carDao.updateCar(carEntity)

    override fun deleteCar(idCar: Long) = carDao.deleteCar(idCar)
}