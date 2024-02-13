package com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryImpl

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.domainLayer.repository.retrofit.ApiCarImmatRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ApiCarImmatRepositoryImpl : ApiCarImmatRepository, KoinComponent {
    private val remoteDataSource by inject<RemoteDataSource>()

    override suspend fun getVehiculeByImmat(immat: String): Flow<Ressource<Car>> {
        return remoteDataSource.getVehiculeByImmat(immat)
    }
}