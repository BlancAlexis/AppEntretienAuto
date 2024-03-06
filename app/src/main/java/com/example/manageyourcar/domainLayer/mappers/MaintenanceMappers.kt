package com.example.manageyourcar.domainLayer.mappers

import com.example.manageyourcar.dataLayer.model.Maintenance
import com.example.manageyourcar.dataLayer.room.entities.MaintenanceEntity

object MaintenanceMappers {
    fun Maintenance.toMaintenanceEntity(): MaintenanceEntity {
        return MaintenanceEntity(
            userID = this.userID,
            carID = this.carID,
            mileage = this.mileage,
            price = this.price,
            date = this.date,
            serviceType = this.service
        )
    }

    fun MaintenanceEntity.toEntretien(): Maintenance {
        return Maintenance(
            userID = this.userID,
            carID = this.carID,
            mileage = this.mileage,
            price = this.price,
            date = this.date,
            service = this.serviceType
        )
    }
}