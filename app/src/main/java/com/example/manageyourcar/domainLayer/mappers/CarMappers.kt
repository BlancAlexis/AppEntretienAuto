package com.example.manageyourcar.domainLayer.mappers

import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.CarEntity
import com.example.manageyourcar.dataLayer.model.CarLocal
import java.util.Date

object CarMappers {
    fun CarLocal.toCarEntity(): CarEntity {
        return CarEntity(
            brand = this.brand,
            fuel = this.fuel,
            model = this.model,
            releaseDate = Date(this.releaseDate.toLong()),
            transmission = this.transmission,
            power = this.power,
            motorization = this.motorization,
            torque = this.torque,
            maxSpeed = this.maxSpeed,
            mileage = this.mileage,
            ownerID = this.ownerID
        )
    }

    fun CarEntity.toCar(): CarLocal {
        return CarLocal(
            brand = this.brand,
            fuel = this.fuel,
            model = this.model,
            releaseDate = this.releaseDate.time.toString(),
            transmission = this.transmission,
            power = this.power,
            motorization = this.motorization,
            torque = this.torque,
            maxSpeed = this.maxSpeed,
            mileage = this.mileage,
            ownerID = this.ownerID,
            carID = this.carID,
        )
    }

}