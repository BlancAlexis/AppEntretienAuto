package com.example.manageyourcar.dataLayer.dataLayerRoom.repositoryImpl

import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.CarDao
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.domainLayer.mappers.CarMappers.toCar
import com.example.manageyourcar.domainLayer.mappers.CarMappers.toCarEntity
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class CarRepositoryImpl(private val carDao: CarDao) : CarRepository, KoinComponent {


    override fun addNewCar(carLocal: CarLocal) {
        carDao.addNewCar(carLocal.toCarEntity())
    }

    override fun getCars(idUser: Int): Flow<List<CarLocal>> {
        return carDao.getCars(idUser).map { carEntitys ->
            carEntitys.map { it.toCar() }
        }
    }

    override fun getCar(idCar: Int): CarLocal {
        return carDao.getCar(idCar).toCar()
    }

    override fun getCars(): List<CarLocal> {
        val brutResult = carDao.getCars()
        val resultCarLocal: MutableList<CarLocal> = arrayListOf()

        for (element in brutResult) {
            resultCarLocal.add(element.toCar())
        }
        return resultCarLocal
    }

    override fun updateCar(carLocal: CarLocal) {
        carDao.updateCar(carLocal.toCarEntity())
    }

    override fun updateCarMileage(listMileages: List<Int>, idCar: Int) {
        carDao.updateCarMileage(listMileages, idCar)
    }

    override fun deleteCar(car : CarLocal) {
        carDao.deleteCar(car.toCarEntity())
    }
}

