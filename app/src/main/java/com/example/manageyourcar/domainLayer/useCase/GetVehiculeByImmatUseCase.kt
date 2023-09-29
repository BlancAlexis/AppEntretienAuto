package com.example.manageyourcar.domainLayer.useCase

import com.example.manageyourcar.dataLayer.model.dataClass.Car
import com.example.manageyourcar.dataLayer.repository.ApiCarImmatRepository
import com.example.manageyourcar.dataLayer.repository.ApiCarSIVRepository
import com.example.manageyourcar.dataLayer.util.Ressource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetVehiculeByImmatUseCase: KoinComponent {
    val api by inject<ApiCarImmatRepository>()
    suspend fun getVehiculeByImmat(immat : String): Flow<Ressource<Car>> {
        return api.getVehiculeByImmat(immat)
    }

}