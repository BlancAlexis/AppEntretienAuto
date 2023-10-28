package com.example.manageyourcar.dataLayer.dataLayerRetrofit.model


import com.google.gson.annotations.SerializedName

data class Rpm(
    @SerializedName("horsepower")
    val horsepower: Int,
    @SerializedName("torque")
    val torque: Int
)