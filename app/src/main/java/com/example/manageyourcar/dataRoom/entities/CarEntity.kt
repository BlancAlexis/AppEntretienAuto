package com.example.manageyourcar.dataRoom.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey(autoGenerate = true,) val id: Int = 0,
    @ColumnInfo(name = "marque") val marque: String,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "dateDeParution") val dateDeParution: Int,
    @ColumnInfo(name = "carburant") val carburant: String,
    @ColumnInfo(name = "transmission") val transmission: String,
    @ColumnInfo(name = "motorisation") val motorisation: String,
    @ColumnInfo(name = "puissance") val puissance: Int,
    @ColumnInfo(name = "couple") val couple: Int,
    @ColumnInfo(name = "vitesseMax") val vitesseMax: Int,
    @ColumnInfo(name = "kilometrage") val kilometrage: Int,
)