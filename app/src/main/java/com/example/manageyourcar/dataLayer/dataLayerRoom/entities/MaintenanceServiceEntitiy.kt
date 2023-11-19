package com.example.manageyourcar.dataLayer.dataLayerRoom.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.manageyourcar.dataLayer.model.MaintenanceServiceType

@Entity(tableName = "maintenance_services")
data class MaintenanceServiceEntitiy(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val category: String,
    val reminder: Int,
    @ColumnInfo(name = "service_type") val serviceType: MaintenanceServiceType
)