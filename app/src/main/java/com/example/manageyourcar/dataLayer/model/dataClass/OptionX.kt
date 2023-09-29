package com.example.manageyourcar.dataLayer.model.dataClass


import com.google.gson.annotations.SerializedName

data class OptionX(
    @SerializedName("category")
    val category: String,
    @SerializedName("options")
    val options: List<com.example.manageyourcar.dataLayer.model.dataClass.OptionXX>
)