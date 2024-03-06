package com.example.manageyourcar.dataLayer.room.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import com.example.manageyourcar.dataLayer.room.entities.CarEntity
import com.example.manageyourcar.dataLayer.room.entities.MaintenanceEntity
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

    @Transaction
    suspend fun upsertServicing(maintenanceEntity: MaintenanceEntity) {
        addNewServicing(maintenanceEntity)
        updateServicing(maintenanceEntity)
    }

    @Query("SELECT * FROM servicing WHERE user_id = :userId")
    @Transaction
    fun getMaintenceActWithCar(userId: Int): Flow<List<MaintenanceWithCarEntity>>
}

data class MaintenanceWithCarEntity(
    @Embedded val maintenanceEntity: MaintenanceEntity,
    @Relation(
        parentColumn = "car_id",
        entityColumn = "carID"
    )
    val carEntity: CarEntity
)
