package com.example.manageyourcar.dataLayer.cache.carDatastore

import com.example.manageyourcar.dataLayer.model.Car


@kotlinx.serialization.Serializable
data class CarCache(
    val carCached: List<Car> = listOf()
)
