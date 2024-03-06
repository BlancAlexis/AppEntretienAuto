package com.example.manageyourcar.domainLayer.mappers

import com.example.manageyourcar.dataLayer.retrofit.model.CarRetrofit
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object CarRetrofitToCar : KoinComponent {
    private val cacheManager by inject<CacheManagerRepository>()

    fun CarRetrofit.toCarGlobal(): com.example.manageyourcar.dataLayer.model.Car {

        return com.example.manageyourcar.dataLayer.model.Car(
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
            ownerID = GlobalScope.launch {
                cacheManager.getUserId().data
            } as Int
        )
    }
}