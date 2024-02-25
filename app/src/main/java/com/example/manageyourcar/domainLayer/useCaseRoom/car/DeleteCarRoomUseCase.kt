package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.dataLayer.dataLayerFirebase.remoteDataFirebaseSource
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteCarRoomUseCase : KoinComponent {
    val roomRepository by inject<remoteDataFirebaseSource>()

    suspend fun deleteCar(car: CarLocal): Ressource<Int> {
        return try {
            println("je tente delete")
            roomRepository.deleteCar(car)
            Ressource.Success(1) //à refacto

        } catch (e: Exception) {
            println("${e}  delete eception")
            Ressource.Error(exception = e)
        }
    }
}