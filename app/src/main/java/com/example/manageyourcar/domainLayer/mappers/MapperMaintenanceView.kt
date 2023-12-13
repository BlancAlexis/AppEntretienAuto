package com.example.manageyourcar.domainLayer.mappers

import com.example.manageyourcar.dataLayer.model.MaintenanceService
import com.example.manageyourcar.dataLayer.model.MaintenanceServiceType

object MapperMaintenanceView {
    fun MaintenanceService.toMaintenanceServiceType(): MaintenanceServiceType {
        return when (this) {
            is MaintenanceService.Vidange -> MaintenanceServiceType.VIDANGE
            is MaintenanceService.Freins -> MaintenanceServiceType.FREINS
            is MaintenanceService.Pneus -> MaintenanceServiceType.PNEUS
        }
    }

    fun MaintenanceServiceType.toMaintenanceService(): MaintenanceService {
        return when (this) {
            MaintenanceServiceType.VIDANGE -> MaintenanceService.Vidange()
            MaintenanceServiceType.FREINS -> MaintenanceService.Freins()
            MaintenanceServiceType.PNEUS -> MaintenanceService.Pneus()
        }
    }
}