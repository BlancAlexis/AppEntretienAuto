package com.example.manageyourcar.UIlayer.composeView.UIState

import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.model.MaintenanceService
import com.example.manageyourcar.dataLayer.model.MaintenanceServiceType
import java.util.Date

data class AddVehiculeMaintenanceUiState (
    val price: Int = 0,
    val listCars : List<Car> = listOf(),
    val selectedCar : Car?= null,
    val listMaintenance : List<Array<MaintenanceServiceType>> = listOf(MaintenanceServiceType.values()),
    val selectedMaintenance : MaintenanceServiceType? = null,
    val mileage : Int = 0,
    val date : Date?= null,

    val onInternetLost: Boolean=false
    ) {

}