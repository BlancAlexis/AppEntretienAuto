package com.example.manageyourcar.UIlayer.UIState

data class AddCarUIState(

    val inputVIN: String? = null,
    val inputImmat: String? = null,

    val errorVIN: String? = null,
    val errorImmat: String? = null,

    val onInternetLost: Boolean = false
)