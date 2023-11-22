package com.example.manageyourcar.domainLayer.mappers

import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.CarEntity
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.MaintenanceEntity
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.dataLayer.model.MaintenanceService
import com.example.manageyourcar.dataLayer.model.MaintenanceServiceType
import com.example.manageyourcar.domainLayer.mappers.MaintenanceMappers.toMaintenanceEntity

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