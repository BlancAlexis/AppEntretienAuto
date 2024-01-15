package com.example.manageyourcar.domainLayer.mappers

import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.MaintenanceEntity
import com.example.manageyourcar.dataLayer.model.Entretien

object MaintenanceMappers {
    fun Entretien.toMaintenanceEntity(): MaintenanceEntity {
        return MaintenanceEntity(
            userID = this.userID,
            carID = this.carID,
            mileage = this.mileage,
            price = this.price,
            date = this.date,
            serviceType = this.service
        )
    }

    fun MaintenanceEntity.toEntretien(): Entretien {
        return Entretien(
            userID = this.userID,
            carID = this.carID,
            mileage = this.mileage,
            price = this.price,
            date = this.date,
            service = this.serviceType
        )
    }
}