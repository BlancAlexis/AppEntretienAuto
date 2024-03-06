package com.example.manageyourcar.dataLayer.retrofit.repositoryImpl

import com.example.manageyourcar.dataLayer.retrofit.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.retrofit.model.Place
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.retrofit.PlacesApiRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlacesApiRepositoryImpl : PlacesApiRepository, KoinComponent {
    private val remoteDataSource by inject<RemoteDataSource>()
    override suspend fun getPlacesBy(
        latitude: Double,
        longitude: Double,
    ): Flow<Ressource<List<Place>>> {
        return remoteDataSource.getCarRepairShopInProximity(latitude, longitude)
    }
}
