package com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryRetrofit

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.model.Place
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GarageRepositoryImpl : GarageRepository, KoinComponent {
    private val remoteDataSource by inject<RemoteDataSource>()
    override suspend fun getPlacesBy(
        latitude: Double,
        longitude: Double,
    ): Flow<Ressource<List<Place>>> {
        return remoteDataSource.getCarRepairShopInProximity(latitude, longitude)
    }
}
