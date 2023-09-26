package com.example.manageyourcar.di

import com.example.manageyourcar.model.requestApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



val appModule = module {
    single<requestApi> {
            Retrofit.Builder()
                .baseUrl("https://api-pokemon-fr.vercel.app/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(requestApi::class.java)

    }
}