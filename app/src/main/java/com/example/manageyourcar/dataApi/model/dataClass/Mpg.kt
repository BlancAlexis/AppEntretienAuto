package com.example.manageyourcar.dataApi.model.dataClass


import com.google.gson.annotations.SerializedName

data class Mpg(
    @SerializedName("city")
    val city: String,
    @SerializedName("highway")
    val highway: String
)