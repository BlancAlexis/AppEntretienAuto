package com.example.manageyourcar.domainLayer.repository.room

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.Entretien
import kotlinx.coroutines.flow.Flow

interface ServicingRepository {
    fun addNewServicing(entretien: Entretien): Ressource<Unit>
    fun getAllUserMaintenance(): Flow<Ressource<List<Entretien>>>
    fun deleteServicing(idServicing: Int): Ressource<Unit>

}