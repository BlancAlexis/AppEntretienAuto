package com.example.manageyourcar.domainLayer.useCaseRoom.car

import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUserCarsUseCase : KoinComponent {
    private val roomRepository by inject<CarRepository>()
    private val cacheManagerRepository by inject<CacheManagerRepository>()

    suspend fun invoke(): Flow<Ressource<List<Car>>> {
        return try {
            when (val result = cacheManagerRepository.getUserId(AppApplication.instance)) {
                is Ressource.Success -> roomRepository.getCars(1)
                    .map { it -> Ressource.Success(it) }
                is Ressource.Error -> flowOf(Ressource.Error(exception = result.error))
                is Ressource.Loading -> flowOf(Ressource.Loading())
            }
        } catch (e: Exception) {
            flowOf(Ressource.Error(exception = e))
        }
    }
}
