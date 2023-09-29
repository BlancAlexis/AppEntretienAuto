package com.example.manageyourcar.model.dataClass


import com.google.gson.annotations.SerializedName

data class Engine(
    @SerializedName("availability")
    val availability: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("compressionRatio")
    val compressionRatio: Double,
    @SerializedName("compressorType")
    val compressorType: String,
    @SerializedName("configuration")
    val configuration: String,
    @SerializedName("cylinder")
    val cylinder: Int,
    @SerializedName("displacement")
    val displacement: Int,
    @SerializedName("equipmentType")
    val equipmentType: String,
    @SerializedName("fuelType")
    val fuelType: String,
    @SerializedName("horsepower")
    val horsepower: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rpm")
    val rpm: Rpm,
    @SerializedName("size")
    val size: Int,
    @SerializedName("torque")
    val torque: Int,
    @SerializedName("totalValves")
    val totalValves: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("valve")
    val valve: ValveX,
)