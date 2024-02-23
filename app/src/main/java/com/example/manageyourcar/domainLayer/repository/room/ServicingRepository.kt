package com.example.manageyourcar.domainLayer.repository.room

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.MaintenanceWithCarEntity
import com.example.manageyourcar.dataLayer.model.Entretien
import kotlinx.coroutines.flow.Flow

interface ServicingRepository {
    fun addNewServicing(entretien: Entretien): Ressource<Unit>
    fun getAllServicing(): Flow<Ressource<List<Entretien>>>
    fun getAllUserMaintenance(): Flow<Ressource<List<Entretien>>>
    fun getServicing(idServicing: Int): Flow<Ressource<Entretien>>
    fun updateServicing(entretien: Entretien): Ressource<Unit>
    fun deleteServicing(idServicing: Int): Ressource<Unit>

    fun getMaintenceActWithCar(userId: Int): Flow<Ressource<List<MaintenanceWithCarEntity>>>
}