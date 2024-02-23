package com.example.manageyourcar.dataLayer

import com.example.manageyourcar.dataLayer.model.CarLocal


@kotlinx.serialization.Serializable
data class CarCache(
    val carCached : List<CarLocal> = listOf()
)
