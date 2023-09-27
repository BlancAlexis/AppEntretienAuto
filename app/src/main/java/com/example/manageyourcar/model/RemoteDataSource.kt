package com.example.manageyourcar.model

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RemoteDataSource(): KoinComponent {
    val api by inject<requestApi>()

    fun getVehiculeBySIV(SIV : String) = flow {
        emit(Ressource.Loading())
        val vehicule = api.getVehiculeBySIV(SIV).body()
        emit(Ressource.Success(vehicule))
    }.catch { cause ->
        emit(Ressource.Error(cause.toString(),null))
    }

}