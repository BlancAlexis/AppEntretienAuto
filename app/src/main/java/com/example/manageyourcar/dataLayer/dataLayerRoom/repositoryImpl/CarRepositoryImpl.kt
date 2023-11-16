package com.example.manageyourcar.dataLayer.dataLayerRoom.repositoryImpl

import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.CarDao
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.domainLayer.mappers.CarMappers.toCar
import com.example.manageyourcar.domainLayer.mappers.CarMappers.toCarEntity
import org.koin.core.component.KoinComponent

class CarRepositoryImpl(private val carDao: CarDao) : CarRepository, KoinComponent {


    override fun addNewCar(car: Car) {
        carDao.addNewCar(car.toCarEntity())
    }

    override fun getCars(idUser: Int): List<Car> {
        val brutResult = carDao.getCars(idUser)
        val resultCar: MutableList<Car> = arrayListOf()

        for (element in brutResult) {
            resultCar.add(element.toCar())
        }
        return resultCar
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

    override fun deleteCar(idCar: Int) {
        carDao.deleteCar(idCar)
    }
}

