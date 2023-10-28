package com.example.manageyourcar.domainLayer.useCaseRetrofit

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.model.Place
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryRetrofit.GarageRepository
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCarRepairShopUseCase : KoinComponent {
    val api by inject<GarageRepository>()

    suspend fun getVehiculeByImmat(
        longitude: Double,
        latitude: Double
    ): Flow<Ressource<List<Place>>> {
        return api.getPlacesBy(latitude, longitude)
    }

}