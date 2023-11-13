package com.example.manageyourcar.dataLayer.model

import java.util.Date

data class Entretien(
    val mileage : Int,
    val price : Int,
    val date: Date,
    val service : MaintenanceService,
)
