package com.example.manageyourcar.dataLayer.retrofit

import com.example.manageyourcar.BuildConfig
import com.example.manageyourcar.dataLayer.retrofit.model.CarRetrofit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RequestApiSIV {
    @GET("vin/{siv}")
    suspend fun getVehiculeBySIV(
        @Path("siv") siv: String,
        @Query("apikey") apikey: String = BuildConfig.AUTO_DEV_API_KEY
    ): Response<CarRetrofit>
}