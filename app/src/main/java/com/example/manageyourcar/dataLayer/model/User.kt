package com.example.manageyourcar.dataLayer.model

data class User(
    val id: Int ?= null,
    val login: String,
    val password: String,
    val firstname: String,
    val lastname: String
)