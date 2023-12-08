package com.example.manageyourcar.domainLayer.repository.room

import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.MaintenanceWithCarEntity
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.dataLayer.model.Servicing
import kotlinx.coroutines.flow.Flow

interface ServicingRepository {
    fun addNewServicing(entretien: Entretien)
    fun getAllServicing(): List<Entretien>
    fun getAllUserMaintenance(): Flow<List<Entretien>>
    fun getServicing(idServicing: Int): Entretien
    fun updateServicing(entretien: Entretien)
    fun deleteServicing(idServicing: Int)

    fun getMaintenceActWithCar(userId: Int): Flow<List<MaintenanceWithCarEntity>>
}