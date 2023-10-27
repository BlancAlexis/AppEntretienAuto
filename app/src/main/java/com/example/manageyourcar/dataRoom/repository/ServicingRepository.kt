package com.example.manageyourcar.dataRoom.repository

import com.example.manageyourcar.dataRoom.model.Servicing

interface ServicingRepository {
    fun addNewServicing(servicing: Servicing)
    fun getAllServicing(): List<Servicing>
    fun getServicing(idServicing: Int): Servicing
    fun updateServicing(servicing: Servicing)
    fun deleteServicing(idServicing: Int)
}