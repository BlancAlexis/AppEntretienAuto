package com.example.manageyourcar.model


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("baseMsrp")
    val baseMsrp: Int,
    @SerializedName("deliveryCharges")
    val deliveryCharges: Int,
    @SerializedName("estimateTmv")
    val estimateTmv: Boolean
)