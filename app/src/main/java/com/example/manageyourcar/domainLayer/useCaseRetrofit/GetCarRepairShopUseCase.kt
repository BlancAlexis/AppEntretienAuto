package com.example.manageyourcar.domainLayer.useCaseRetrofit

import com.example.manageyourcar.dataApi.model.dataClass.Car
import com.example.manageyourcar.dataApi.model.dataClass.Place
import com.example.manageyourcar.dataApi.repositoryRetrofit.ApiCarImmatRepository
import com.example.manageyourcar.dataApi.repositoryRetrofit.GarageRepository
import com.example.manageyourcar.dataApi.util.Ressource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCarRepairShopUseCase() : KoinComponent {
    val api by inject<GarageRepository>()

    suspend fun getVehiculeByImmat(longitude : Double, latitude : Double): Flow<Ressource<List<Place>>> {
        return api.getPlacesBy(latitude, longitude)
    }

}