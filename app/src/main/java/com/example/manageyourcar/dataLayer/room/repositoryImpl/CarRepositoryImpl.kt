package com.example.manageyourcar.dataLayer.room.repositoryImpl

import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.room.dao.CarDao
import com.example.manageyourcar.domainLayer.mappers.CarMappers.toCar
import com.example.manageyourcar.domainLayer.mappers.CarMappers.toCarEntity
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class CarRepositoryImpl(private val carDao: CarDao) : CarRepository, KoinComponent {


    override fun addNewCar(car: Car) {
        carDao.addNewCar(car.toCarEntity())
    }

    override fun getCars(idUser: Int): Flow<List<Car>> {
        return carDao.getCars(idUser).map { carEntitys ->
            carEntitys.map { it.toCar() }
        }
    }

    override fun getCar(idCar: Int): Car {
        return carDao.getCar(idCar).toCar()
    }

    override fun getCars(): List<Car> {
        val brutResult = carDao.getCars()
        val resultCar: MutableList<Car> = arrayListOf()

        for (element in brutResult) {
            resultCar.add(element.toCar())
        }
        return resultCar
    }

    override fun updateCar(car: Car) {
        carDao.updateCar(car.toCarEntity())
    }

    override fun updateCarMileage(listMileages: List<Int>, idCar: Int) {
        carDao.updateCarMileage(listMileages, idCar)
    }

    override fun deleteCar(car: Car) {
        carDao.deleteCar(car.toCarEntity())
    }
}

