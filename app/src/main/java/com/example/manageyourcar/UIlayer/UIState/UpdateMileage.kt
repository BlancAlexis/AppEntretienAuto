package com.example.manageyourcar.UIlayer.UIState

import com.example.manageyourcar.dataLayer.model.Car

data class UpdateMileage(
    val car: Car? = null,
    val newMileage: String? = null
)