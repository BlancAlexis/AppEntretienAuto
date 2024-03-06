package com.example.manageyourcar.domainLayer.repository.room

import com.example.manageyourcar.dataLayer.model.Maintenance
import com.example.manageyourcar.dataLayer.room.dao.MaintenanceWithCarEntity
import kotlinx.coroutines.flow.Flow

interface ServicingRepository {
    fun addNewServicing(maintenance: Maintenance)
    fun getAllServicing(): List<Maintenance>
    fun getAllUserMaintenance(): Flow<List<Maintenance>>
    fun getServicing(idServicing: Int): Maintenance
    fun updateServicing(maintenance: Maintenance)
    fun deleteServicing(idServicing: Int)

    fun getMaintenceActWithCar(userId: Int): Flow<List<MaintenanceWithCarEntity>>
}