package com.example.manageyourcar.dataLayer.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

@kotlinx.serialization.Serializable
data class Car(
    val carID: Int? = -1,
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
) : Serializable
