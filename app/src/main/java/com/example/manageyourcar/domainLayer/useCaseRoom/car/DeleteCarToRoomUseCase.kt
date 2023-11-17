package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteCarToRoomUseCase : KoinComponent {
    val roomRepository by inject<CarRepository>()

    suspend fun deleteCarToRoom(idCar: Int) : Ressource<Unit> {
        return try {
                    roomRepository.deleteCar(idCar)
                    Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }
}