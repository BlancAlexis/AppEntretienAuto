package com.example.manageyourcar.dataRoom.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

data class Car (
    val id: Int,
    val marque: String,
    val model: String,
    val dateDeParution: Int,
    val carburant: String,
    val transmission: String,
    val motorisation: String,
    val puissance: Int,
    val couple: Int,
    val vitesseMax: Int,
    val kilometrage: Int
)