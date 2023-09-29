package com.example.manageyourcar.dataLayer.repository.room.car

import com.example.manageyourcar.dataLayer.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.dataSource.room.CarEntity
import com.example.manageyourcar.dataLayer.model.dataClass.room.Car
import com.example.manageyourcar.dataLayer.repository.MyRepository
import com.example.manageyourcar.dataLayer.service.room.car.CarDatabaseService
import com.example.manageyourcar.dataLayer.util.Ressource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CarRepositoryImpl : CarRepository,KoinComponent {
    val carDatabaseService by inject<CarDatabaseService>()
    override suspend fun addNewCar(car: Car) {
        val carEntity = CarEntity(
            marque = car.marque,
            model = car.model,
        )
        carDatabaseService.addNewCar(carEntity)
    }

    override suspend fun getCar(): Flow<List<Car>> =
        carDatabaseService.getCar().map { entities ->
            entities.map {
                Car(
                    id = it.id,
                    marque = it.marque,
                    model = it.model,
                )
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun updateCar(car: Car) {
        val carEntity = CarEntity(
            id = car.id,
            marque = car.marque,
            model = car.model,
        )

        carDatabaseService.updateCar(carEntity)
    }

    override suspend fun deleteCar(idCar: Long) {
        carDatabaseService.deleteCar(idCar)
    }
}
