package com.example.manageyourcar.UIlayer.composeView.UIState

import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.model.MaintenanceServiceType
import java.util.Date

data class AddVehiculeMaintenanceUiState(
    val price: Int = 0,
    val listCars: List<Car> = listOf(),
    val listMaintenance: List<MaintenanceServiceType>  = listOf(),
    val mileage: Int = 0,
    val date: Date?= null,

    val onInternetLost: Boolean=false
    ) {

}