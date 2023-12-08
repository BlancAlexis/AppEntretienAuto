package com.example.manageyourcar.dataLayer.dataLayerRoom.repositoryImpl

import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.MaintenanceWithCarEntity
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.ServicingDao
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.MaintenanceEntity
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.domainLayer.repository.room.ServicingRepository
import com.example.manageyourcar.dataLayer.model.Servicing
import com.example.manageyourcar.domainLayer.mappers.CarMappers.toCar
import com.example.manageyourcar.domainLayer.mappers.MaintenanceMappers.toEntretien
import com.example.manageyourcar.domainLayer.mappers.MaintenanceMappers.toMaintenanceEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class ServicingRepositoryImpl(private val servicingDao: ServicingDao) : ServicingRepository,KoinComponent {
    override fun addNewServicing(entretien: Entretien) {
        servicingDao.addNewServicing(entretien.toMaintenanceEntity())
    }

    override fun getAllServicing(): List<Entretien> {
        return servicingDao.getServicing().map { it ->
            it.toEntretien()
        }
    }

    override fun getAllUserMaintenance(): Flow<List<Entretien>> {
        return servicingDao.getUserServicing().map { it ->
            it.map{ it.toEntretien() }
        }
    }

    override fun getServicing(idServicing: Int): Entretien {
        return servicingDao.getServicing(idServicing).toEntretien()
    }

    override fun updateServicing(entretien: Entretien) {
        servicingDao.updateServicing(entretien.toMaintenanceEntity())
    }

    override fun deleteServicing(idServicing: Int) {
        servicingDao.deleteServicing(idServicing)
    }

    override fun getMaintenceActWithCar(userId: Int): Flow<List<MaintenanceWithCarEntity>> {
        return servicingDao.getMaintenceActWithCar(userId)
    }

}