package com.example.manageyourcar.domainLayer.mappers

import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.CarEntity
import com.example.manageyourcar.dataLayer.model.Car

object CarMappers {
    fun Car.toCarEntity(): CarEntity {
        return CarEntity(
            brand = this.brand,
            fuel = this.fuel,
            model = this.model,
            releaseDate = this.releaseDate,
            transmission = this.transmission,
            power = this.power,
            motorization = this.motorization,
            torque = this.torque,
            maxSpeed = this.maxSpeed,
            mileage = this.mileage,
            ownerID = this.ownerID
        )
    }

    fun CarEntity.toCar(): Car {
        return Car(
            brand = this.brand,
            fuel = this.fuel,
            model = this.model,
            releaseDate = this.releaseDate,
            transmission = this.transmission,
            power = this.power,
            motorization = this.motorization,
            torque = this.torque,
            maxSpeed = this.maxSpeed,
            mileage = this.mileage,
            ownerID = this.ownerID,
            carID = this.carID
        )
    }

}