package com.example.manageyourcar.model

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET


interface requestApi {
    @GET("pokemon")

    suspend fun getAllPokemon(): Flow<Ressource<Unit>>
}