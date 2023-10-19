package com.example.manageyourcar.dataApi.model.dataClass


import com.google.gson.annotations.SerializedName

data class Year(
    @SerializedName("id")
    val id: Int,
    @SerializedName("styles")
    val styles: List<com.example.manageyourcar.dataApi.model.dataClass.Style>,
    @SerializedName("year")
    val year: Int
)