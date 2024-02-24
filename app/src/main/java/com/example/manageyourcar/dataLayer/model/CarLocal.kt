package com.example.manageyourcar.dataLayer.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.UUID

@kotlinx.serialization.Serializable
data class CarLocal(
    val carID: String = UUID.randomUUID().toString(),
    @SerializedName("marque")
    val brand: String,
    @SerializedName("modele")
    val model: String,
    @SerializedName("annee")
    val releaseDate: String,
    @SerializedName("carburant")
    val fuel: String,
    val transmission: String,
    @SerializedName("motorisation")
    val motorization: String,
    @SerializedName("puissance")
    val power: Int,
    @SerializedName("couple")
    val torque: Int,
    @SerializedName("vitessemax")
    val maxSpeed: Int,
    val mileage: List<Int>,
    val ownerID: Int? = null
) : Serializable {
    constructor() : this(brand = "", model = "", releaseDate = "", fuel = "", transmission ="", motorization = "", power = 0, torque = 0, maxSpeed = 0, mileage = listOf(), ownerID = 0)
}
