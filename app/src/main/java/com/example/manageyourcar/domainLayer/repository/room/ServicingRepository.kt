package com.example.manageyourcar.domainLayer.repository.room

import com.example.manageyourcar.dataLayer.model.Servicing

interface ServicingRepository {
    fun addNewServicing(servicing: Servicing)
    fun getAllServicing(): List<Servicing>
    fun getServicing(idServicing: Int): Servicing
    fun updateServicing(servicing: Servicing)
    fun deleteServicing(idServicing: Int)
}