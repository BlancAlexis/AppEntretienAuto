package com.example.manageyourcar.dataLayer.dataLayerRoom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.MaintenanceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicingDao {
    @Insert
    fun addNewServicing(maintenanceEntity: MaintenanceEntity)

    @Query("SELECT * FROM servicing")
    fun getServicing(): List<MaintenanceEntity>
    @Query("SELECT * FROM servicing")
    fun getUserServicing(): Flow<List<MaintenanceEntity>>
    @Query("SELECT * FROM servicing where user_id = :userID")
    fun getUserMaintenances(userID: Int): Flow<List<MaintenanceEntity>>

    @Query("SELECT * FROM servicing WHERE id = :idServicing")
    fun getServicing(idServicing: Int): MaintenanceEntity

    @Update
    fun updateServicing(entretienEntity: MaintenanceEntity)

    @Query("DELETE FROM servicing WHERE id = :idServicing")
    fun deleteServicing(idServicing: Int)
}