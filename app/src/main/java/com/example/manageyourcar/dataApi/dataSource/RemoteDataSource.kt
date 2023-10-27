package com.example.manageyourcar.dataApi.dataSource

import com.example.manageyourcar.dataApi.requestApiImmat
import com.example.manageyourcar.dataApi.util.Ressource
import com.example.manageyourcar.dataApi.requestApiSIV
import com.example.manageyourcar.dataApi.garageApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RemoteDataSource() : KoinComponent {
    val requestApiSIV by inject<requestApiSIV>()
    val requestAPIImmat by inject<requestApiImmat>()
    val garageApi by inject<garageApi>()

    suspend fun getVehiculeBySIV(SIV: String) = flow {
        emit(Ressource.Loading())
        val vehicule = requestApiSIV.getVehiculeBySIV(SIV).body()
        emit(Ressource.Success(vehicule))
    }.catch { cause ->
        emit(Ressource.Error(cause.toString(), null))
    }

    suspend fun getVehiculeByImmat(immat: String) = flow {
        emit(Ressource.Loading())
        val vehicule = requestAPIImmat.getVehiculeByImmat(immat).body()
        emit(Ressource.Success(vehicule))
    }.catch { cause ->
        emit(Ressource.Error(cause.toString(), null))
    }

    suspend fun getCarRepairShopInProximity(latitude : Double, longitude : Double) = flow {
        emit(Ressource.Loading())
        val CarRepairShops = garageApi.fetchPlaces(apiKey= "AIzaSyAOwiPT1Z6g_REZXEk3toO7uRZYR_TAm_0",radius = 20000, location = "$latitude+,+$longitude").body()
        emit(Ressource.Success(CarRepairShops))
    }.catch { cause ->
        emit(Ressource.Error(cause.toString(), null))
    }
}
