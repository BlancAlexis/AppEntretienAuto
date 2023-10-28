package com.example.manageyourcar.dataLayer.dataLayerRetrofit.model


import com.google.gson.annotations.SerializedName

data class Year(
    @SerializedName("id")
    val id: Int,
    @SerializedName("styles")
    val styles: List<Style>,
    @SerializedName("year")
    val year: Int
)