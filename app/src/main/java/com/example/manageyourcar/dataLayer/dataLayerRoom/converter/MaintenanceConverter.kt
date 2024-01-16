package com.example.manageyourcar.dataLayer.dataLayerRoom.converter

import androidx.room.TypeConverter
import com.example.manageyourcar.dataLayer.model.MaintenanceService

class MaintenanceServiceConverter {

    @TypeConverter
    fun fromMaintenanceService(service: MaintenanceService): String {
        return when (service) {
            is MaintenanceService.Vidange -> "Vidange"
            is MaintenanceService.Freins -> "Freins"
            is MaintenanceService.Pneus -> "Pneus"
        }
    }

    @TypeConverter
    fun toMaintenanceService(serviceType: String): MaintenanceService {
        return when (serviceType) {
            "Vidange" -> MaintenanceService.Vidange()
            "Freins" -> MaintenanceService.Freins()
            "Pneus" -> MaintenanceService.Pneus()
            else -> throw IllegalArgumentException("Invalid service type: $serviceType")
        }
    }
}