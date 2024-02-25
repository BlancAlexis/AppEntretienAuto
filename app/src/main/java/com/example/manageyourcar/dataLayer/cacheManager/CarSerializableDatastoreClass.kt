package com.example.manageyourcar.dataLayer.cacheManager

import com.example.manageyourcar.dataLayer.model.CarLocal


@kotlinx.serialization.Serializable
data class CarSerializableDatastoreClass(
    val carCached : List<CarLocal> = listOf()
)
