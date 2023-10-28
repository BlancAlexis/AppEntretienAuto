package com.example.manageyourcar.domainLayer.useCaseRetrofit

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.model.Car
import com.example.manageyourcar.domainLayer.repository.retrofit.ApiCarImmatRepository
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetVehiculeByNetworkImmatUseCase : KoinComponent {
    val api by inject<ApiCarImmatRepository>()

    suspend fun getVehiculeByImmat(immat: String): Flow<Ressource<Car>> {
        return api.getVehiculeByImmat(immat)
    }

}