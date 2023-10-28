package com.example.manageyourcar.dataLayer.dataLayerRetrofit.model


import com.google.gson.annotations.SerializedName

data class Mpg(
    @SerializedName("city")
    val city: String,
    @SerializedName("highway")
    val highway: String
)