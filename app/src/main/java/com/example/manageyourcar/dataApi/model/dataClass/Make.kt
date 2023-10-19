package com.example.manageyourcar.dataApi.model.dataClass


import com.google.gson.annotations.SerializedName

data class Make(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("niceName")
    val niceName: String
)