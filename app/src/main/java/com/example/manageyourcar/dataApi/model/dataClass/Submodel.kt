package com.example.manageyourcar.dataApi.model.dataClass


import com.google.gson.annotations.SerializedName

data class Submodel(
    @SerializedName("body")
    val body: String,
    @SerializedName("modelName")
    val modelName: String,
    @SerializedName("niceName")
    val niceName: String
)