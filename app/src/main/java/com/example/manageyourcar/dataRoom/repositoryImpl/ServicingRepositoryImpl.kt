package com.example.manageyourcar.dataRoom.repositoryImpl

import com.example.manageyourcar.dataRoom.dao.ServicingDao
import com.example.manageyourcar.dataRoom.entities.ServicingEntity
import com.example.manageyourcar.dataRoom.model.Servicing
import com.example.manageyourcar.dataRoom.repository.ServicingRepository
import org.koin.core.component.KoinComponent

class ServicingRepositoryImpl(private val servicingDao: ServicingDao): ServicingRepository, KoinComponent {
    override fun addNewServicing(servicing: Servicing){
        val servicingEntity = ServicingEntity(
            name = servicing.name,
            eachKilometre = servicing.eachKilometre,
        )

        servicingDao.addNewServicing(servicingEntity);
    }

    override fun getAllServicing(): List<Servicing> {
        val brutResult = servicingDao.getServicing();
        val resultServicing: MutableList<Servicing> = arrayListOf();

        for (element in brutResult){
            resultServicing.add(Servicing(
                element.id,
                element.name,
                element.eachKilometre
            ))
        }

        return resultServicing;
    }

    override fun getServicing(idServicing: Int): Servicing {
        val brutResult = servicingDao.getServicing(idServicing);

        return Servicing(
            id = brutResult.id,
            name = brutResult.name,
            eachKilometre = brutResult.eachKilometre
        )
    }

    override fun updateServicing(servicing: Servicing) {
        val servicingEntity = ServicingEntity(
            id = servicing.id,
            name = servicing.name,
            eachKilometre = servicing.eachKilometre
        )

        servicingDao.updateServicing(servicingEntity);
    }

    override fun deleteServicing(idServicing: Int) {
        servicingDao.deleteServicing(idServicing);
    }

}