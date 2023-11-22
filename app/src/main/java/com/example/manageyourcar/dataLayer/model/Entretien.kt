package com.example.manageyourcar.dataLayer.model

import androidx.room.Embedded
import java.util.Date

data class Entretien(
    val userID : Int?=null,
    val carID : Int?=null,
    val mileage : Int,
    val price : Int,
    val date: Date,
    @Embedded
    val service : MaintenanceService,
)
