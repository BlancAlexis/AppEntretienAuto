package com.example.manageyourcar.model

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface requestApi {
    @GET("vin/{siv}")

    suspend fun getVehiculeBySIV(
        @Path("siv") siv : String,
        @Query("apikey") apikey : String = "ZrQEPSkKbmFydXRvLmJsYW5jQGdtYWlsLmNvbQ=="
    ): Response<Unit>
}