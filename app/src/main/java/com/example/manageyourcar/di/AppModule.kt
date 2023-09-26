package com.example.manageyourcar.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AppModule {

/*    @Provides
    @Singleton

    }*/

}

val appModule = module {
    single {
        fun provideRetrofitInstance(): requestApi {
            return Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(requestApi::class.java)
        }
    }
}