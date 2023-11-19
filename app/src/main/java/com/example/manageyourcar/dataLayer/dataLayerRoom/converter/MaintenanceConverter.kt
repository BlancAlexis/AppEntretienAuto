package com.example.manageyourcar.dataLayer.dataLayerRoom.converter

import androidx.room.TypeConverter
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.MaintenanceServiceEntitiy
import com.example.manageyourcar.dataLayer.model.MaintenanceService
import com.example.manageyourcar.dataLayer.model.MaintenanceServiceType

class MaintenanceConverter {
    @TypeConverter
    fun convertMaintenanceServiceToEnum(service: MaintenanceService): MaintenanceServiceType {
        return when (service) {
            is MaintenanceService.Vidange -> MaintenanceServiceType.VIDANGE
            is MaintenanceService.Freins -> MaintenanceServiceType.FREINS
            is MaintenanceService.Pneus -> MaintenanceServiceType.PNEUS
        }
    }
@TypeConverter
    fun convertEnumToMaintenanceService(serviceType: MaintenanceServiceType): MaintenanceService {
        return when (serviceType) {
            MaintenanceServiceType.VIDANGE -> MaintenanceService.Vidange()
            MaintenanceServiceType.FREINS -> MaintenanceService.Freins()
            MaintenanceServiceType.PNEUS -> MaintenanceService.Pneus()
        }
    }
}