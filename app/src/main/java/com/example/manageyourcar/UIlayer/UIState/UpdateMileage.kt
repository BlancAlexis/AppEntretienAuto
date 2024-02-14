package com.example.manageyourcar.UIlayer.UIState

import com.example.manageyourcar.dataLayer.model.CarLocal

data class UpdateMileage(
    val carLocal: CarLocal? = null,
    val newMileage: String? = null
)