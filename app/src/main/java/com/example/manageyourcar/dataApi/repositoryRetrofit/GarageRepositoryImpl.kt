package com.example.manageyourcar.dataApi.repositoryRetrofit

import com.example.manageyourcar.dataApi.dataSource.RemoteDataSource
import com.example.manageyourcar.dataApi.model.dataClass.Place
import com.example.manageyourcar.dataApi.util.Ressource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GarageRepositoryImpl (): GarageRepository, KoinComponent {
    private val remoteDataSource by inject<RemoteDataSource>()
    override suspend fun getPlacesBy(
        latitude: Double,
        longitude: Double,
    ): Flow<Ressource<List<Place>>> {
        return remoteDataSource.getCarRepairShopInProximity(latitude, longitude)
    }
}
