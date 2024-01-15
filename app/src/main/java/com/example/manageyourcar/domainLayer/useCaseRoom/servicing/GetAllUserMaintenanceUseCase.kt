package com.example.manageyourcar.domainLayer.useCaseRoom.servicing

import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.MaintenanceWithCarEntity
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.repository.room.ServicingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetAllUserMaintenanceUseCase: KoinComponent {
    private val roomRepository by inject<ServicingRepository>()
    private val cacheManagerRepository by inject<CacheManagerRepository>()

    suspend fun invoke(): Flow<Ressource<List<MaintenanceWithCarEntity>>> {
        return try {
            when (val result = cacheManagerRepository.getUserId(AppApplication.instance)) {
                is Ressource.Success -> roomRepository.getMaintenceActWithCar(result.data!!).map { it -> Ressource.Success(it) }
                is Ressource.Error -> flowOf(Ressource.Error(exception = result.error))
                is Ressource.Loading -> flowOf(Ressource.Loading())
            }
        } catch (e: Exception) {
            flowOf(Ressource.Error(exception = e))
        }
    }
}
