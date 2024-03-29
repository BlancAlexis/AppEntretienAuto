package com.example.manageyourcar.domainLayer.mappers

import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.room.entities.CarEntity
import java.util.Date

object CarMappers {
    fun Car.toCarEntity(): CarEntity {
        return CarEntity(
            carID = this.carID ?: 0,
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

    fun CarEntity.toCar(): Car {
        return Car(
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