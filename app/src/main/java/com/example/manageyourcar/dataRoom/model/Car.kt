package com.example.manageyourcar.dataRoom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Car (
    val id: Long = 0,
    val marque: String,
    val model: String,
)