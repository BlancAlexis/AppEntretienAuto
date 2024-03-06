package com.example.manageyourcar.dataLayer.room.repositoryImpl

import com.example.manageyourcar.dataLayer.model.Maintenance
import com.example.manageyourcar.dataLayer.room.dao.MaintenanceWithCarEntity
import com.example.manageyourcar.dataLayer.room.dao.ServicingDao
import com.example.manageyourcar.domainLayer.mappers.MaintenanceMappers.toEntretien
import com.example.manageyourcar.domainLayer.mappers.MaintenanceMappers.toMaintenanceEntity
import com.example.manageyourcar.domainLayer.repository.room.ServicingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class ServicingRepositoryImpl(private val servicingDao: ServicingDao) : ServicingRepository,
    KoinComponent {
    override fun addNewServicing(maintenance: Maintenance) {
        servicingDao.addNewServicing(maintenance.toMaintenanceEntity())
    }

    override fun getAllServicing(): List<Maintenance> {
        return servicingDao.getServicing().map {
            it.toEntretien()
        }
    }

    override fun getAllUserMaintenance(): Flow<List<Maintenance>> {
        return servicingDao.getUserServicing().map {
            it.map { it.toEntretien() }
        }
    }

    override fun getServicing(idServicing: Int): Maintenance {
        return servicingDao.getServicing(idServicing).toEntretien()
    }

    override fun updateServicing(maintenance: Maintenance) {
        servicingDao.updateServicing(maintenance.toMaintenanceEntity())
    }

    override fun deleteServicing(idServicing: Int) {
        servicingDao.deleteServicing(idServicing)
    }

    override fun getMaintenceActWithCar(userId: Int): Flow<List<MaintenanceWithCarEntity>> {
        return servicingDao.getMaintenceActWithCar(userId)
    }

}