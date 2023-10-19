package com.example.manageyourcar.dataApi.dataSource

import com.example.manageyourcar.dataApi.requestApiImmat
import com.example.manageyourcar.dataApi.util.Ressource
import com.example.manageyourcar.dataApi.requestApiSIV
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RemoteDataSource() : KoinComponent {
    val requestApiSIV by inject<requestApiSIV>()
    val requestAPIImmat by inject<requestApiImmat>()

    suspend fun getVehiculeBySIV(SIV: String) = flow {
        emit(Ressource.Loading())
        val vehicule = requestApiSIV.getVehiculeBySIV(SIV).body()
        emit(Ressource.Success(vehicule))
    }.catch { cause ->
        emit(Ressource.Error(cause.toString(), null))
    }

    fun getVehiculeByImmat(immat: String) = flow {
        emit(Ressource.Loading())
        val vehicule = requestAPIImmat.getVehiculeByImmat(immat).body()
        emit(Ressource.Success(vehicule))
    }.catch { cause ->
        emit(Ressource.Error(cause.toString(), null))
    }
}
