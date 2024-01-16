package com.example.manageyourcar.dataLayer.dataLayerRetrofit.dataSource

import com.example.manageyourcar.BuildConfig
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.RequestApiImmat
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.RequestApiSIV
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.placesApi
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RemoteDataSource : KoinComponent {
    val requestApiSIV by inject<RequestApiSIV>()
    val requestAPIImmat by inject<RequestApiImmat>()
    val placesApi by inject<placesApi>()
    private val PLACES_API_KEY = BuildConfig.PLACES_API_KEY
    suspend fun getVehiculeBySIV(SIV: String) = flow {
        emit(Ressource.Loading())
        val vehicule = requestApiSIV.getVehiculeBySIV(SIV).body()
        emit(Ressource.Success(vehicule))
    }.catch { cause ->
        emit(Ressource.Error(message = cause.toString()))
    }

    suspend fun getVehiculeByImmat(immat: String) = flow {
        emit(Ressource.Loading())
        val vehicule = requestAPIImmat.getVehiculeByImmat(immat).body()
        emit(Ressource.Success(vehicule))
    }.catch { cause ->
        emit(Ressource.Error(message = cause.toString()))
    }

    suspend fun getCarRepairShopInProximity(latitude: Double, longitude: Double) = flow {
        emit(Ressource.Loading())
        val CarRepairShops = placesApi.fetchPlaces(
            apiKey = PLACES_API_KEY,
            radius = 20000,
            location = "$latitude+,+$longitude"
        ).body()
        emit(Ressource.Success(CarRepairShops))
    }.catch { cause ->
        emit(Ressource.Error(message = cause.toString()))
    }
}
