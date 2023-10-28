package com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryRetrofit

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.model.Car
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ApiCarImmatRepositoryImpl : ApiCarImmatRepository, KoinComponent {
    private val remoteDataSource by inject<RemoteDataSource>()

    override suspend fun getVehiculeByImmat(immat: String): Flow<Ressource<Car>> {
        return remoteDataSource.getVehiculeByImmat(immat)
    }
}