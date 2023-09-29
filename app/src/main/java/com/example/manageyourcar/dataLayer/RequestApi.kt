package com.example.manageyourcar.dataLayer

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface requestApiSIV {
    @GET("vin/{siv}")
    suspend fun getVehiculeBySIV(
        @Path("siv") siv: String,
        @Query("apikey") apikey: String = "ZrQEPSkKbmFydXRvLmJsYW5jQGdtYWlsLmNvbQ=="
    ): Response<com.example.manageyourcar.dataLayer.model.dataClass.Car>
}