package com.example.manageyourcar.dataApi.repositoryRetrofit

import com.example.manageyourcar.dataApi.dataSource.RemoteDataSource
import com.example.manageyourcar.dataApi.model.dataClass.Car
import com.example.manageyourcar.dataApi.util.Ressource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ApiCarSIVRepositoryImpl (): ApiCarSIVRepository, KoinComponent{
    private val remoteDataSource by inject<RemoteDataSource>()

    override suspend fun getVehiculeBySiv(siv: String): Flow<Ressource<Car>> {
        return remoteDataSource.getVehiculeBySIV(siv)
    }


}
