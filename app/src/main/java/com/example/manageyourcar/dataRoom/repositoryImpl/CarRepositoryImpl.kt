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
            dateDeParution = car.dateDeParution,
            carburant = car.carburant,
            transmission = car.transmission,
            motorisation = car.motorisation,
            puissance = car.puissance,
            couple = car.couple,
            vitesseMax = car.vitesseMax,
            kilometrage = car.kilometrage
        )
        carDao.addNewCar(carEntity)
    }

    override fun getCar(idCar: Int): Car{
        val brutResult = carDao.getCar(idCar);

        return Car(brutResult.id,
            brutResult.marque,
            brutResult.model,
            brutResult.dateDeParution,
            brutResult.carburant,
            brutResult.transmission,
            brutResult.motorisation,
            brutResult.puissance,
            brutResult.couple,
            brutResult.vitesseMax,
            brutResult.kilometrage);
    }

    override fun getCars(): List<Car> {
        val brutResult = carDao.getCars();
        val resultCar: MutableList<Car> = arrayListOf();

        for(element in brutResult){
            resultCar.add(Car(0,
                element.marque,
                element.model,
                element.dateDeParution,
                element.carburant,
                element.transmission,
                element.motorisation,
                element.puissance,
                element.couple,
                element.vitesseMax,
                element.kilometrage))
        }

        return resultCar;
    }

    override fun updateCar(car: Car) {
        val carEntity = CarEntity(
            id = car.id,
            marque = car.marque,
            model = car.model,
            dateDeParution = car.dateDeParution,
            carburant = car.carburant,
            transmission = car.transmission,
            motorisation = car.motorisation,
            puissance = car.puissance,
            couple = car.couple,
            vitesseMax = car.vitesseMax,
            kilometrage = car.kilometrage
        )
        carDao.updateCar(carEntity)
    }

    override fun deleteCar(idCar: Int) {
        carDao.deleteCar(idCar);
    }
}
