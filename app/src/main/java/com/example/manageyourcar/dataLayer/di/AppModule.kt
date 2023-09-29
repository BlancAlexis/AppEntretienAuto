package com.example.manageyourcar.dataLayer.di

import com.example.manageyourcar.dataLayer.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.repository.ApiCarSIVRepository
import com.example.manageyourcar.dataLayer.repository.ApiCarSIVRepositoryImpl
import com.example.manageyourcar.dataLayer.requestApiSIV
import com.example.manageyourcar.dataLayer.util.RequestLoggingInterceptor
import com.example.manageyourcar.domainLayer.useCase.GetVehiculeByNetworkUseCase
import okhttp3.OkHttpClient
import com.example.manageyourcar.UIlayer.viewmodel.UserViewModel
import com.example.manageyourcar.dataLayer.repository.ApiCarImmatRepository
import com.example.manageyourcar.dataLayer.repository.ApiCarImmatRepositoryImpl
import com.example.manageyourcar.dataLayer.requestApiImmat
import com.example.manageyourcar.domainLayer.useCase.GetVehiculeByImmatUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



val appModule = module {
    //Instance Retrofit en singleton
    single<requestApiSIV> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestLoggingInterceptor())
            .build()
        Retrofit.Builder()
            .baseUrl("https://auto.dev/api")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(requestApiSIV::class.java)
    }
    single<requestApiImmat  > {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestLoggingInterceptor())
            .build()
        Retrofit.Builder()
                .baseUrl("https://apimobile.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(requestApiImmat::class.java)
    }

    // UseCase en singleton
    single { GetVehiculeByNetworkUseCase() }
    single { GetVehiculeByImmatUseCase() }

    // Vérif traffic rétrofit
    factory<RequestLoggingInterceptor> { RequestLoggingInterceptor() }

    
    factory<ApiCarImmatRepository> { ApiCarImmatRepositoryImpl() }
    factory<ApiCarSIVRepository> { ApiCarSIVRepositoryImpl() }
    factory<RemoteDataSource> { RemoteDataSource() }
    viewModelOf(::UserViewModel)
}