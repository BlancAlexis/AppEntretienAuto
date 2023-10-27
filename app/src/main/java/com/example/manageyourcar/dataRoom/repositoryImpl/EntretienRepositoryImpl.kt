package com.example.manageyourcar.dataRoom.repositoryImpl

import com.example.manageyourcar.dataRoom.dao.EntretienDao
import com.example.manageyourcar.dataRoom.entities.EntretienEntity
import com.example.manageyourcar.dataRoom.model.Entretien
import com.example.manageyourcar.dataRoom.repository.EntretienRepository
import org.koin.core.component.KoinComponent

class EntretienRepositoryImpl(private val entretienDao: EntretienDao): EntretienRepository, KoinComponent {
    override fun addNewEntretien(entretien: Entretien){
        val entretienEntity = EntretienEntity(
            name = entretien.name,
            iterationKilometre = entretien.iterationKilometre,
        )

        entretienDao.addNewEntretien(entretienEntity);
    }

    override fun getEntretiens(): List<Entretien> {
        val brutResult = entretienDao.getEntretiens();
        val resultEntretien: MutableList<Entretien> = arrayListOf();

        for (element in brutResult){
            resultEntretien.add(Entretien(
                element.id,
                element.name,
                element.iterationKilometre
            ))
        }

        return resultEntretien;
    }

    override fun getEntretien(idEntretien: Int): Entretien {
        val brutResult = entretienDao.getEntretien(idEntretien);

        return Entretien(
            id = brutResult.id,
            name = brutResult.name,
            iterationKilometre = brutResult.iterationKilometre
        )
    }

    override fun updateEntretien(entretien: Entretien) {
        val entretienEntity = EntretienEntity(
            id = entretien.id,
            name = entretien.name,
            iterationKilometre = entretien.iterationKilometre
        )

        entretienDao.updateEntretien(entretienEntity);
    }

    override fun deleteEntretien(idEntretien: Int) {
        entretienDao.deleteEntretien(idEntretien);
    }

}