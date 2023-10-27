package com.example.manageyourcar.dataRoom.repository

import com.example.manageyourcar.dataRoom.model.Entretien

interface EntretienRepository {
    fun addNewEntretien(entretien: Entretien)
    fun getEntretiens(): List<Entretien>
    fun getEntretien(idEntretien: Int): Entretien
    fun updateEntretien(entretien: Entretien)
    fun deleteEntretien(idEntretien: Int)
}