package com.example.manageyourcar.domainLayer.useCaseRetrofit

import com.example.manageyourcar.dataLayer.retrofit.model.CarRetrofit
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.retrofit.ApiCarSIVRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetVehiculeByNetworkUseCase : KoinComponent {
    val api by inject<ApiCarSIVRepository>()

    suspend fun getVehiculeBySiv(siv: String): Flow<Ressource<CarRetrofit>> {
        return api.getVehiculeBySiv(siv)
    }


}