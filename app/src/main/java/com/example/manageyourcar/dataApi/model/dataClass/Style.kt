package com.example.manageyourcar.dataApi.model.dataClass


import com.google.gson.annotations.SerializedName

data class Style(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("submodel")
    val submodel: com.example.manageyourcar.dataApi.model.dataClass.Submodel,
    @SerializedName("trim")
    val trim: String
)