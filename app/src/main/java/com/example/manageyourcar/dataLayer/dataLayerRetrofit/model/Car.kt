package com.example.manageyourcar.dataLayer.dataLayerRetrofit.model

import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("categories")
    val categories: Categories,
    @SerializedName("colors")
    val colors: List<Color>,
    @SerializedName("drivenWheels")
    val drivenWheels: String,
    @SerializedName("engine")
    val engine: Engine,
    @SerializedName("make")
    val make: Make,
    @SerializedName("matchingType")
    val matchingType: String,
    @SerializedName("model")
    val model: Model,
    @SerializedName("mpg")
    val mpg: Mpg,
    @SerializedName("numOfDoors")
    val numOfDoors: String,
    @SerializedName("options")
    val options: List<OptionX>,
    @SerializedName("price")
    val price: Price,
    @SerializedName("squishVin")
    val squishVin: String,
    @SerializedName("transmission")
    val transmission: Transmission,
    @SerializedName("years")
    val years: List<Year>
)