package com.example.manageyourcar.domainLayer.useCaseRoom

import com.example.manageyourcar.dataLayer.model.dataClass.room.Car
import com.example.manageyourcar.dataLayer.repository.room.car.CarRepository
import com.example.manageyourcar.dataLayer.util.Ressource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.Flow

class GetCarFromRoom : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun getCarFromRoom() {
        roomRepository.getCar()
    }
}