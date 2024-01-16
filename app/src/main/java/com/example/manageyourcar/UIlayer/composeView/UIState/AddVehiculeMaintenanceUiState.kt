package com.example.manageyourcar.UIlayer.composeView.UIState

import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.model.MaintenanceServiceType
import java.util.Date

data class AddVehiculeMaintenanceUiState(
    val price: String = "",
    val listCars: List<Car> = listOf(),
    val listMaintenance: List<MaintenanceServiceType> = listOf(),
    val mileage: String = "",
    val date: Date? = null,

    val pricePlahlder: String = "Prix",
    val mileagePlahlder: String = "Kilométrage",

    val onInternetLost: Boolean = false
)