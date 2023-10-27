package com.example.manageyourcar.dataRoom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.manageyourcar.dataRoom.entities.EntretienEntity

@Dao
interface EntretienDao {
    @Insert
    fun addNewEntretien(entretienEntity: EntretienEntity)
    @Query("SELECT * FROM entretiens")
    fun getEntretiens() : List<EntretienEntity>

    @Query("SELECT * FROM entretiens WHERE id = :idEntretien")
    fun getEntretien(idEntretien: Int) : EntretienEntity

    @Update
    fun updateEntretien(entretienEntity: EntretienEntity)

    @Query("DELETE FROM entretiens WHERE id = :idEntretien")
    fun deleteEntretien(idEntretien: Int)
}