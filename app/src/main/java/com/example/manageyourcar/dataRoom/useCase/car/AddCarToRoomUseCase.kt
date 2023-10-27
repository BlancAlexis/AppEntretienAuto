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
                             marque: String,
                             model: String,
                             dateDeParution: Int,
                             carburant: String,
                             transmission: String,
                             motorisation: String,
                             puissance: Int,
                             couple: Int,
                             vitesseMax: Int,
                             kilometrage: Int) {

        roomRepository.addNewCar(Car(id,
            marque,
            model,
            dateDeParution,
            carburant,
            transmission,
            motorisation,
            puissance,
            couple,
            vitesseMax,
            kilometrage))
    }
}