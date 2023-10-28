package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.dataLayer.dataLayerRoom.repository.CarRepository
import com.example.manageyourcar.dataLayer.model.Car
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddCarToRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun addCarToRoom(
        id: Int,
        brand: String,
        model: String,
        releaseDate: Int,
        fuel: String,
        transmission: String,
        motorization: String,
        power: Int,
        torque: Int,
        maxSpeed: Int,
        mileage: Int
    ) {

        roomRepository.addNewCar(
            Car(
                id,
                brand,
                model,
                releaseDate,
                fuel,
                transmission,
                motorization,
                power,
                torque,
                maxSpeed,
                mileage
            )
        )
    }
}