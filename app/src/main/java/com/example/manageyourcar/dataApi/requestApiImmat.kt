package com.example.manageyourcar.dataApi

import com.example.manageyourcar.dataApi.model.dataClass.Car
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface requestApiImmat {
    @GET("/getallcar")
    suspend fun getVehiculeByImmat(
        @Query("plaqueimmat") immat : String
    ): Response<Car>
}