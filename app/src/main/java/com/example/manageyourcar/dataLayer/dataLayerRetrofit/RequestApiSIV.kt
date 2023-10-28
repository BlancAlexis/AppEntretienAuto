package com.example.manageyourcar.dataLayer.dataLayerRetrofit

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.model.Car
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface requestApiSIV {
    @GET("vin/{siv}")
    suspend fun getVehiculeBySIV(
        @Path("siv") siv: String,
        @Query("apikey") apikey: String = "ZrQEPSkKbmFydXRvLmJsYW5jQGdtYWlsLmNvbQ=="
    ): Response<Car>
}