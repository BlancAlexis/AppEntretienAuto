package com.example.manageyourcar.model.dataClass


import com.google.gson.annotations.SerializedName

data class OptionX(
    @SerializedName("category")
    val category: String,
    @SerializedName("options")
    val options: List<OptionXX>
)