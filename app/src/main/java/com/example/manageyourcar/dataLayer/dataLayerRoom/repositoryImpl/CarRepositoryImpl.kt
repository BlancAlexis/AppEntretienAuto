package com.example.manageyourcar.dataLayer.dataLayerRoom.repositoryImpl

import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.CarDao
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.CarEntity
import com.example.manageyourcar.dataLayer.dataLayerRoom.repository.CarRepository
import com.example.manageyourcar.dataLayer.model.Car
import org.koin.core.component.KoinComponent

class CarRepositoryImpl(private val carDao: CarDao) : CarRepository, KoinComponent {

    override fun addNewCar(car: Car) {
        val carEntity = CarEntity(
            brand = car.brand,
            model = car.model,
            releaseDate = car.releaseDate,
            fuel = car.fuel,
            transmission = car.transmission,
            motorization = car.motorization,
            power = car.power,
            torque = car.torque,
            maxSpeed = car.maxSpeed,
            mileage = car.mileage
        )
        carDao.addNewCar(carEntity)
    }

    override fun getCar(idCar: Int): Car {
        val brutResult = carDao.getCar(idCar)

        return Car(
            brutResult.id,
            brutResult.brand,
            brutResult.model,
            brutResult.releaseDate,
            brutResult.fuel,
            brutResult.transmission,
            brutResult.motorization,
            brutResult.power,
            brutResult.torque,
            brutResult.maxSpeed,
            brutResult.mileage
        )
    }

    override fun getCars(): List<Car> {
        val brutResult = carDao.getCars()
        val resultCar: MutableList<Car> = arrayListOf()

        for (element in brutResult) {
            resultCar.add(
                Car(
                    0,
                    element.brand,
                    element.model,
                    element.releaseDate,
                    element.fuel,
                    element.transmission,
                    element.motorization,
                    element.power,
                    element.torque,
                    element.maxSpeed,
                    element.mileage
                )
            )
        }

        return resultCar
    }

    override fun updateCar(car: Car) {
        val carEntity = CarEntity(
            id = car.id,
            brand = car.brand,
            model = car.model,
            releaseDate = car.releaseDate,
            fuel = car.fuel,
            transmission = car.transmission,
            motorization = car.motorization,
            power = car.power,
            torque = car.torque,
            maxSpeed = car.maxSpeed,
            mileage = car.mileage
        )
        carDao.updateCar(carEntity)
    }

    override fun deleteCar(idCar: Int) {
        carDao.deleteCar(idCar)
    }
}
