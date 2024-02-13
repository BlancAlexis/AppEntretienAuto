package com.example.manageyourcar.dataLayer.dataLayerRetrofit.model

import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("engine")
    val engine: Engine,
    @SerializedName("make")
    val make: Make,
    @SerializedName("model")
    val model: Model,
    @SerializedName("transmission")
    val transmission: Transmission,
    @SerializedName("years")
    val years: List<Year>
)