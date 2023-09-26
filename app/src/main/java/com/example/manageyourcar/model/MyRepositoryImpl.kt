package com.example.manageyourcar.model

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class MyRepositoryImpl (): MyRepository, KoinComponent{
    private val remoteDataSource by inject<RemoteDataSource>()

    override suspend fun getVehiculeBySiv(siv: String): Flow<Ressource<D>> {
        remoteDataSource.getVehiculeBySIV(siv)
    }

}
