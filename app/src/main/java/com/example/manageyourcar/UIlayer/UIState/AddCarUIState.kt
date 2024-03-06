package com.example.manageyourcar.UIlayer.UIState

import com.example.manageyourcar.dataLayer.model.Car


data class AddCarUIState(

    val inputVIN: String? = null,
    val inputImmat: String? = null,

    val errorVIN: String? = null,
    val errorImmat: String? = null,

    val carFind: Car? = null
)