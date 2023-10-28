package com.example.manageyourcar.dataLayer.dataLayerRetrofit.model


import com.google.gson.annotations.SerializedName

data class OptionXX(
    @SerializedName("availability")
    val availability: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("equipmentType")
    val equipmentType: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)