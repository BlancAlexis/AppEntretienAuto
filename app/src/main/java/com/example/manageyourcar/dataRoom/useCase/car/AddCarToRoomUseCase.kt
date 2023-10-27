package com.example.manageyourcar.dataRoom.useCase.car

import com.example.manageyourcar.dataApi.model.dataClass.Transmission
import com.example.manageyourcar.dataRoom.model.Car
import com.example.manageyourcar.dataRoom.repository.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Date

class AddCarToRoomUseCase : KoinComponent{
    val roomRepository by inject<CarRepository>()

    suspend fun addCarToRoom(id: Int,
                             brand: String,
                             model: String,
                             releaseDate: Int,
                             fuel: String,
                             transmission: String,
                             motorization: String,
                             power: Int,
                             torque: Int,
                             maxSpeed: Int,
                             mileage: Int) {

        roomRepository.addNewCar(Car(id,
            brand,
            model,
            releaseDate,
            fuel,
            transmission,
            motorization,
            power,
            torque,
            maxSpeed,
            mileage))
    }
}