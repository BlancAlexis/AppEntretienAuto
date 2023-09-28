package com.example.manageyourcar.model


import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("crossover")
    val crossover: String,
    @SerializedName("epaClass")
    val epaClass: String,
    @SerializedName("market")
    val market: String,
    @SerializedName("primaryBodyType")
    val primaryBodyType: String,
    @SerializedName("vehicleSize")
    val vehicleSize: String,
    @SerializedName("vehicleStyle")
    val vehicleStyle: String,
    @SerializedName("vehicleType")
    val vehicleType: String
)