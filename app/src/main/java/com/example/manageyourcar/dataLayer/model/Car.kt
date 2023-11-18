package com.example.manageyourcar.dataLayer.model

data class Car(
    val carID: Int? = null,
    val brand: String,
    val model: String,
    val releaseDate: Int,
    val fuel: String,
    val transmission: String,
    val motorization: String,
    val power: Int,
    val torque: Int,
    val maxSpeed: Int,
    val mileage: Int,
    val ownerID: Int? = null
) {

}