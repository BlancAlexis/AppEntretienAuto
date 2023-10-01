package com.example.manageyourcar.dataLayer.repository.room.car

import com.example.manageyourcar.dataLayer.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.dataSource.room.CarDao
import com.example.manageyourcar.dataLayer.dataSource.room.CarEntity
import com.example.manageyourcar.dataLayer.model.dataClass.room.Car
import com.example.manageyourcar.dataLayer.repository.MyRepository
import com.example.manageyourcar.dataLayer.util.Ressource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CarRepositoryImpl(private val carDao: CarDao) : CarRepository,KoinComponent {

    override fun addNewCar(car: Car) {
        val carEntity = CarEntity(
            marque = car.marque,
            model = car.model,
        )
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

    }

    override fun deleteCar(idCar: Long) {
    }
}
