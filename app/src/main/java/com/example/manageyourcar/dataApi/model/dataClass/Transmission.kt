package com.example.manageyourcar.dataApi.model.dataClass


import com.google.gson.annotations.SerializedName

data class Transmission(
    @SerializedName("automaticType")
    val automaticType: String,
    @SerializedName("availability")
    val availability: String,
    @SerializedName("equipmentType")
    val equipmentType: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("numberOfSpeeds")
    val numberOfSpeeds: String,
    @SerializedName("transmissionType")
    val transmissionType: String
)