package com.example.manageyourcar.dataLayer.model.dataClass


import com.google.gson.annotations.SerializedName

data class Style(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("submodel")
    val submodel: com.example.manageyourcar.dataLayer.model.dataClass.Submodel,
    @SerializedName("trim")
    val trim: String
)