package com.example.manageyourcar.model

import com.google.gson.annotations.SerializedName

data class Car(
@SerializedName("name")
val model : String) {
}