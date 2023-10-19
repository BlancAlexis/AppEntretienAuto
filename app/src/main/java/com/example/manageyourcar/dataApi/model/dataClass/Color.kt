package com.example.manageyourcar.dataApi.model.dataClass


import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("category")
    val category: String,
    @SerializedName("options")
    val options: List<com.example.manageyourcar.dataApi.model.dataClass.Option>
)