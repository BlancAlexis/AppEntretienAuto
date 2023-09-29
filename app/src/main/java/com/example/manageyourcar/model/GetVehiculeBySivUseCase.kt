package com.example.manageyourcar.model

import com.example.manageyourcar.model.dataClass.Car
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetVehiculeBySivUseCase (): KoinComponent {
    val api by inject<MyRepository>()

    suspend fun getVehiculeBySiv(SIV : String): Flow<Ressource<Car>> {
        return api.getVehiculeBySiv(SIV)
    }
}