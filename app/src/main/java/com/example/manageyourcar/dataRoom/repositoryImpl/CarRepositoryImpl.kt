package com.example.manageyourcar.dataRoom.repositoryImpl

import com.example.manageyourcar.dataRoom.dao.CarDao
import com.example.manageyourcar.dataRoom.entities.CarEntity
import com.example.manageyourcar.dataRoom.model.Car
import com.example.manageyourcar.dataRoom.repository.CarRepository
import org.koin.core.component.KoinComponent

class CarRepositoryImpl(private val carDao: CarDao) : CarRepository,KoinComponent {

    override fun addNewCar(car: Car) {
        val carEntity = CarEntity(
            marque = car.marque,
            model = car.model,
        )
        carDao.addNewCar(carEntity)
    }

    override fun getCar(): List<Car> {
        val brutResult = carDao.getCar();
        val resultCar: MutableList<Car> = arrayListOf();

        for(element in brutResult){
            resultCar.add(Car(0, element.marque, element.model))
        }

        return resultCar;
    }

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