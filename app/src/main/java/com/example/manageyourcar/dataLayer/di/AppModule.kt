package com.example.manageyourcar.dataLayer.di

import com.example.manageyourcar.dataLayer.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.repository.MyRepository
import com.example.manageyourcar.dataLayer.repository.MyRepositoryImpl
import com.example.manageyourcar.dataLayer.requestApi
import com.example.manageyourcar.dataLayer.util.RequestLoggingInterceptor
import com.example.manageyourcar.domainLayer.useCase.GetVehiculeByNetworkUseCase
import okhttp3.OkHttpClient
import com.example.manageyourcar.UIlayer.viewmodel.UserViewModel
import com.example.manageyourcar.dataLayer.dataSource.room.CarDao
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



val appModule = module {
    single<requestApi> {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestLoggingInterceptor())
            .build()

        Retrofit.Builder()
                .baseUrl("https://apimobile.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(requestApi::class.java)
    }
    single { GetVehiculeByNetworkUseCase() }
    factory<RequestLoggingInterceptor> { RequestLoggingInterceptor() }
    factory<MyRepository> { MyRepositoryImpl() }
    factory<RemoteDataSource> { RemoteDataSource() }
    viewModelOf(::UserViewModel)
}