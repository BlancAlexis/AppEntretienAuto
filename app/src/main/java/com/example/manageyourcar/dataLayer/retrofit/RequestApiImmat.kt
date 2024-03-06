package com.example.manageyourcar.dataLayer.retrofit

import com.example.manageyourcar.dataLayer.model.Car
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestApiImmat {
    @GET("/getCar")
    suspend fun getVehiculeByImmat(
        @Query("plaqueimmat") immat: String
    ): Response<Car>
}