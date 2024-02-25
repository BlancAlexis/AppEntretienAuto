package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.dataLayer.dataLayerFirebase.CarFirestoreDataSource
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteCarRoomUseCase : KoinComponent {
    val roomRepository by inject<CarFirestoreDataSource>()

    suspend operator fun invoke (car: CarLocal): Ressource<String> {
        return try {
            roomRepository.deleteCar(car)
            Ressource.Success(car.carID)

        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }
}