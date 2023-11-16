package com.example.manageyourcar.dataLayer.dataLayerRoom.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey(autoGenerate = true) val carID: Int = 0,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "releaseDate") val releaseDate: Int,
    @ColumnInfo(name = "fuel") val fuel: String,
    @ColumnInfo(name = "transmission") val transmission: String,
    @ColumnInfo(name = "motorization") val motorization: String,
    @ColumnInfo(name = "power") val power: Int,
    @ColumnInfo(name = "torque") val torque: Int,
    @ColumnInfo(name = "maxSpeed") val maxSpeed: Int,
    @ColumnInfo(name = "mileage") val mileage: Int,
)