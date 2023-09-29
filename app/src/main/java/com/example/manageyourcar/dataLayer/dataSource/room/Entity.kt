package com.example.manageyourcar.dataLayer.dataSource.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "password") val password: String,
)

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "marque") val marque: String,
    @ColumnInfo(name = "model") val model: String,
)