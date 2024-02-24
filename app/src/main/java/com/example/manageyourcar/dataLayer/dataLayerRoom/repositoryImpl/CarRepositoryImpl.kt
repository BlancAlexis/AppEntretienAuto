package com.example.manageyourcar.dataLayer.dataLayerRoom.repositoryImpl

import com.example.manageyourcar.dataLayer.dataLayerFirebase.remoteDataFirebaseSource
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.CarDao
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.domainLayer.mappers.CarMappers.toCar
import com.example.manageyourcar.domainLayer.mappers.CarMappers.toCarEntity
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class CarRepositoryImpl(private val remoteDataFirebaseSource: remoteDataFirebaseSource) : CarRepository, KoinComponent {


    override fun addNewCar(carLocal: CarLocal): Ressource<Unit> {
        return remoteDataFirebaseSource.addNewCar(carLocal.toCarEntity())
    }

    override fun getCars(idUser: Int): Flow<Ressource<List<CarLocal>>> {
        return remoteDataFirebaseSource.getCars(idUser).map{ carEntitys ->
            Ressource.Success (carEntitys.data?.map { it.toCar() })
        }
    }

    override fun getCar(idCar: Int): Flow<Ressource<CarLocal>> {
        return remoteDataFirebaseSource.getCar(idCar).map { Ressource.Success(it.data?.toCar()) }
    }

    override fun getCars(): List<CarLocal> {
/*        val brutResult = carDao.getCars()
        val resultCarLocal: MutableList<CarLocal> = arrayListOf()

        for (element in brutResult) {
            resultCarLocal.add(element.toCar())
        }
        return resultCarLocal*/
        return emptyList()
    }

    override fun updateCar(carLocal: CarLocal): Flow<Ressource<Unit>> {
        return  remoteDataFirebaseSource.updateCar(carLocal.toCarEntity())
    }

    override fun updateCarMileage(listMileages: List<Int>, idCar: Int): Flow<Ressource<Unit>> {
         return remoteDataFirebaseSource.updateCarMileage(listMileages, idCar)
    }

    override fun deleteCar(car: CarLocal): Flow<Ressource<Unit>> {
        return remoteDataFirebaseSource.deleteCar(car.toCarEntity())
    }
}

