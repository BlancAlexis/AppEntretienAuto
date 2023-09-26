package com.example.manageyourcar.model

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetVehiculeBySivUseCase (): KoinComponent {
    val api by inject<requestApi>()

    suspend fun getVehiculeBySiv(SIV : String){
        return api.getVehiculeBySIV(SIV)
    }
}