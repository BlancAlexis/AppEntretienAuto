package com.example.manageyourcar.dataRoom.model

data class User (
    val id: Int = 0,
    val login: String,
    val password: String,
    val firstname: String,
    val lastname: String
)