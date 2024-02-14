package com.example.manageyourcar.domainLayer.mappers

import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.model.Car
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object CarRetrofitToCar : KoinComponent {
    private val cacheManager by inject<CacheManagerRepository>()

    fun Car.toCarGlobal(): com.example.manageyourcar.dataLayer.model.CarLocal {
        return com.example.manageyourcar.dataLayer.model.CarLocal(
            carID = null,
            brand = this.make.name,
            model = this.model.name,
            releaseDate = this.years[0].year.toString(),
            transmission = this.transmission.transmissionType,
            motorization = this.engine.fuelType,
            fuel = this.engine.fuelType,
            power = this.engine.horsepower,
            torque = this.engine.torque,
            maxSpeed = 0,
            mileage = listOf(0),
            ownerID = cacheManager.getUserId(context = AppApplication.instance.applicationContext).data
        )
    }
}