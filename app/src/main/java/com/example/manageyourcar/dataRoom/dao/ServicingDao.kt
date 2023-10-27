package com.example.manageyourcar.dataRoom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.manageyourcar.dataRoom.entities.ServicingEntity

@Dao
interface ServicingDao {
    @Insert
    fun addNewServicing(servicingEntity: ServicingEntity)
    @Query("SELECT * FROM servicing")
    fun getServicing() : List<ServicingEntity>

    @Query("SELECT * FROM servicing WHERE id = :idServicing")
    fun getServicing(idServicing: Int) : ServicingEntity

    @Update
    fun updateServicing(entretienEntity: ServicingEntity)

    @Query("DELETE FROM servicing WHERE id = :idServicing")
    fun deleteServicing(idServicing: Int)
}