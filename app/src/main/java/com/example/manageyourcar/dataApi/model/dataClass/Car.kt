package com.example.manageyourcar.dataApi.model.dataClass

import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("categories")
    val categories: com.example.manageyourcar.dataApi.model.dataClass.Categories,
    @SerializedName("colors")
    val colors: List<com.example.manageyourcar.dataApi.model.dataClass.Color>,
    @SerializedName("drivenWheels")
    val drivenWheels: String,
    @SerializedName("engine")
    val engine: com.example.manageyourcar.dataApi.model.dataClass.Engine,
    @SerializedName("make")
    val make: com.example.manageyourcar.dataApi.model.dataClass.Make,
    @SerializedName("matchingType")
    val matchingType: String,
    @SerializedName("model")
    val model: com.example.manageyourcar.dataApi.model.dataClass.Model,
    @SerializedName("mpg")
    val mpg: com.example.manageyourcar.dataApi.model.dataClass.Mpg,
    @SerializedName("numOfDoors")
    val numOfDoors: String,
    @SerializedName("options")
    val options: List<com.example.manageyourcar.dataApi.model.dataClass.OptionX>,
    @SerializedName("price")
    val price: com.example.manageyourcar.dataApi.model.dataClass.Price,
    @SerializedName("squishVin")
    val squishVin: String,
    @SerializedName("transmission")
    val transmission: com.example.manageyourcar.dataApi.model.dataClass.Transmission,
    @SerializedName("years")
    val years: List<com.example.manageyourcar.dataApi.model.dataClass.Year>
){
}