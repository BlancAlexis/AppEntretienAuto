package com.example.manageyourcar.dataLayer.dataLayerRetrofit.model


import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("category")
    val category: String,
    @SerializedName("options")
    val options: List<Option>
)