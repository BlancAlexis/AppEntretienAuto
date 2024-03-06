package com.example.manageyourcar.dataLayer.model

import androidx.room.Embedded
import androidx.room.TypeConverters
import com.example.manageyourcar.dataLayer.room.converter.MaintenanceServiceConverter
import java.util.Date

data class Maintenance(
    val userID: Int? = null,
    val carID: Int? = null,
    val mileage: Int,
    val price: Int,
    val date: Date,
    @TypeConverters(MaintenanceServiceConverter::class)
    @Embedded
    val service: MaintenanceService,
)
