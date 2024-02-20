package com.example.manageyourcar.UIlayer.UIState

import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.dataLayer.model.MaintenanceServiceType
import java.util.Date

data class AddVehiculeMaintenanceUiState(
    val price: String = "",
    val listCarLocals: List<CarLocal> = listOf(),
    val listMaintenance: List<MaintenanceServiceType> = listOf(),
    val mileage: String = "",
    val date: Date? = null,

    val pricePlahlder: String = "Prix",
    val mileagePlahlder: String = "Kilométrage",
)