package com.example.manageyourcar.model


import com.google.gson.annotations.SerializedName

data class Style(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("submodel")
    val submodel: Submodel,
    @SerializedName("trim")
    val trim: String
)