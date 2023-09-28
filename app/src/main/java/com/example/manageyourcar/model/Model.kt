package com.example.manageyourcar.model


import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("niceName")
    val niceName: String
)