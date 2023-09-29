package com.example.manageyourcar.dataLayer

import com.example.manageyourcar.dataLayer.model.dataClass.Car
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface requestApiImmat {
    @GET("/getallcar")
    suspend fun getVehiculeByImmat(
        @Query("plaqueimmat") immat : String
    ): Response<Car>
}