package com.example.manageyourcar.domainLayer.useCaseRetrofit

import com.example.manageyourcar.dataLayer.retrofit.model.Place
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.retrofit.PlacesApiRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCarRepairShopUseCase : KoinComponent {
    val api by inject<PlacesApiRepository>()

    suspend fun getVehiculeByImmat(
        longitude: Double,
        latitude: Double
    ): Flow<Ressource<List<Place>>> {
        return api.getPlacesBy(latitude, longitude)
    }

}