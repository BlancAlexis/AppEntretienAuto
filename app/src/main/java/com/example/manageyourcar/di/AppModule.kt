package com.example.manageyourcar.di

import com.example.manageyourcar.model.GetVehiculeBySivUseCase
import com.example.manageyourcar.model.MyRepository
import com.example.manageyourcar.model.MyRepositoryImpl
import com.example.manageyourcar.model.RemoteDataSource
import com.example.manageyourcar.model.requestApi
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



val appModule = module {
    single<requestApi> {
            Retrofit.Builder()
                .baseUrl("https://auto.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(requestApi::class.java)
    }
    single { GetVehiculeBySivUseCase() }
    factory<MyRepository> { MyRepositoryImpl() }
    factory<RemoteDataSource> { RemoteDataSource() }
}