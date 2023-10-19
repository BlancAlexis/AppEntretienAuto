package com.example.manageyourcar.data.repository

import com.example.manageyourcar.data.database.CarDao
import com.example.manageyourcar.domain.model.CarEntity
import com.example.manageyourcar.domain.model.Car
import com.example.manageyourcar.domain.repository.CarRepository
import org.koin.core.component.KoinComponent

class CarRepositoryImpl(private val carDao: CarDao) : CarRepository,KoinComponent {

    override fun addNewCar(car: Car) {
        val carEntity = CarEntity(
            marque = car.marque,
            model = car.model,
        )
        carDao.addNewCar(carEntity)
    }

 /*   override fun getCar(): Flow<List<Car>> {
        TODO("Not yet implemented")
    }
*/
    override fun updateCar(car: Car) {
        val carEntity = CarEntity(
            id = car.id,
            marque = car.marque,
            model = car.model,
        )
        carDao.updateCar(carEntity)
    }

    override fun deleteCar(idCar: Long) {
    }
}
