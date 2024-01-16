package com.example.manageyourcar.dataLayer.dataLayerRoom.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.manageyourcar.dataLayer.model.MaintenanceService
import java.util.Date

@Entity(
    tableName = "servicing", foreignKeys = arrayOf(
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("userID"),
            childColumns = arrayOf("user_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CarEntity::class,
            parentColumns = arrayOf("carID"),
            childColumns = arrayOf("car_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class MaintenanceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "user_id", index = true) val userID: Int? = null,
    @ColumnInfo(name = "car_id", index = true) val carID: Int? = null,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "mileage") val mileage: Int,
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "service_type") val serviceType: MaintenanceService

)