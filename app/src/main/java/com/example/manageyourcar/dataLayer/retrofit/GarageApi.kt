package com.example.manageyourcar.dataLayer.retrofit

import com.example.manageyourcar.dataLayer.retrofit.model.Place
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface placesApi {
    @GET("/maps/api/place/nearbysearch/json")
    suspend fun fetchPlaces(
        @Query("key") apiKey: String,
        @Query("type") type: String = "car_repair",
        @Query("radius") radius: Int = 10000,
        @Query("location") location: String = "46.2276,5.7295"
    ): Response<List<Place>>

}