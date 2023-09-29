package com.example.manageyourcar.model

import com.example.manageyourcar.model.dataClass.Car
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MyRepositoryImpl (): MyRepository, KoinComponent{
    private val remoteDataSource by inject<RemoteDataSource>()

    override suspend fun getVehiculeBySiv(siv: String): Flow<Ressource<Car>> {
        return remoteDataSource.getVehiculeBySIV(siv)
    }

    override suspend fun getVehiculeByImmat(immat: String): Flow<Ressource<Car>> {
        return remoteDataSource.getVehiculeByImmat(immat)
    }

}
