package com.example.manageyourcar.dataLayer.dataLayerRoom.repositoryImpl

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.MaintenanceWithCarEntity
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.ServicingDao
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.domainLayer.mappers.MaintenanceMappers.toEntretien
import com.example.manageyourcar.domainLayer.mappers.MaintenanceMappers.toMaintenanceEntity
import com.example.manageyourcar.domainLayer.repository.room.ServicingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class ServicingRepositoryImpl(private val servicingDao: ServicingDao) : ServicingRepository,
    KoinComponent {
    override fun addNewServicing(entretien: Entretien): Ressource<Unit> {
        return Ressource.Success(servicingDao.addNewServicing(entretien.toMaintenanceEntity()))
    }

    override fun getAllServicing(): Flow<Ressource<List<Entretien>>> {
        TODO("Not yet implemented")
    }

    override fun getAllUserMaintenance(): Flow<Ressource<List<Entretien>>> {
        TODO("Not yet implemented")
    }

    override fun getServicing(idServicing: Int): Flow<Ressource<Entretien>> {
        TODO("Not yet implemented")
    }

    override fun updateServicing(entretien: Entretien): Ressource<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteServicing(idServicing: Int): Ressource<Unit> {
        TODO("Not yet implemented")
    }

    override fun getMaintenceActWithCar(userId: Int): Flow<Ressource<List<MaintenanceWithCarEntity>>> {
        TODO("Not yet implemented")
    }

    /* override fun getAllServicing(): Flow<Ressource<List<Entretien>>> {
         return Ressource.Success(servicingDao.getServicing().map {
             it.toEntretien()
         }
     }

     override fun getAllUserMaintenance(): Flow<List<Entretien>> {
         return servicingDao.getUserServicing().map {
             it.map { it.toEntretien() }
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
 */
}