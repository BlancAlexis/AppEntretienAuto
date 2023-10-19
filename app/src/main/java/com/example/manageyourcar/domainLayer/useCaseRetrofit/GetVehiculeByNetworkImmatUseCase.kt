package com.example.manageyourcar.domainLayer.useCaseRetrofit

import com.example.manageyourcar.dataApi.model.dataClass.Car
import com.example.manageyourcar.dataApi.repositoryRetrofit.ApiCarImmatRepository
import com.example.manageyourcar.dataApi.util.Ressource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetVehiculeByNetworkImmatUseCase(): KoinComponent {
    val api by inject<ApiCarImmatRepository>()

    suspend fun getVehiculeByImmat(immat : String): Flow<Ressource<Car>> {
        return api.getVehiculeByImmat(immat)
    }

}