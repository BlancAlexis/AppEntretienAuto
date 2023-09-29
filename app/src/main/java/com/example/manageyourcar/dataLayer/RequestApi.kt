package com.example.manageyourcar.dataLayer

import com.example.manageyourcar.dataLayer.model.dataClass.Car
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface requestApi {
    @GET("vin/{siv}")
    suspend fun getVehiculeBySIV(
        @Path("siv") siv: String,
        @Query("apikey") apikey: String = "ZrQEPSkKbmFydXRvLmJsYW5jQGdtYWlsLmNvbQ=="
    ): Response<com.example.manageyourcar.dataLayer.model.dataClass.Car>

@GET("/getallcar")
suspend fun getVehiculeByImmat(
@Query("plaqueimmat") immat : String
): Response<com.example.manageyourcar.dataLayer.model.dataClass.Car>
}