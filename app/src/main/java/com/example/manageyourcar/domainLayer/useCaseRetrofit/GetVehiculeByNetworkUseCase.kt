package com.example.manageyourcar.domainLayer.useCaseRetrofit

import com.example.manageyourcar.dataLayer.model.dataClass.Car
import com.example.manageyourcar.dataLayer.repository.MyRepository
import com.example.manageyourcar.dataLayer.util.Ressource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetVehiculeByNetworkUseCase (): KoinComponent {
    val api by inject<MyRepository>()

    suspend fun getVehiculeBySiv(SIV : String): Flow<Ressource<Car>> {
        return api.getVehiculeBySiv(SIV)
    }

    suspend fun getVehiculeByImmat(immat : String): Flow<Ressource<Car>> {
        return api.getVehiculeByImmat(immat)
    }

}