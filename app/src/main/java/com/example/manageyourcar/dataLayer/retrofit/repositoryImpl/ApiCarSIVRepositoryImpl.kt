package com.example.manageyourcar.dataLayer.retrofit.repositoryImpl

import com.example.manageyourcar.dataLayer.retrofit.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.retrofit.model.CarRetrofit
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.retrofit.ApiCarSIVRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ApiCarSIVRepositoryImpl : ApiCarSIVRepository, KoinComponent {
    private val remoteDataSource by inject<RemoteDataSource>()

    override suspend fun getVehiculeBySiv(siv: String): Flow<Ressource<CarRetrofit>> {
        return remoteDataSource.getVehiculeBySIV(siv)
    }


}
