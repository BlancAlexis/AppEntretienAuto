package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.dataLayer.dataLayerFirebase.remoteDataFirebaseSource
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpsertCarMileageUseCase : KoinComponent {
    val roomRepository by inject<remoteDataFirebaseSource>()

    suspend operator fun invoke(carLocal: CarLocal): Ressource<Unit> {
        return try {
            roomRepository.updateCarMileage(carLocal.mileage,  carLocal.carID) // TODO
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(exception = e)
        }
    }
}