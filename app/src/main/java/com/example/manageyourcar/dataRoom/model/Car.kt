package com.example.manageyourcar.dataRoom.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

data class Car (
    val id: Int,
    val brand: String,
    val model: String,
    val releaseDate: Int,
    val fuel: String,
    val transmission: String,
    val motorization: String,
    val power: Int,
    val torque: Int,
    val maxSpeed: Int,
    val mileage: Int
)