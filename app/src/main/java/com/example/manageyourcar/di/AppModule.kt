package com.example.manageyourcar.di

import com.example.manageyourcar.model.GetVehiculeBySivUseCase
import com.example.manageyourcar.model.MyRepository
import com.example.manageyourcar.model.MyRepositoryImpl
import com.example.manageyourcar.model.RemoteDataSource
import com.example.manageyourcar.model.RequestLoggingInterceptor
import com.example.manageyourcar.model.requestApi
import okhttp3.OkHttpClient
import org.koin.core.scope.get
import com.example.manageyourcar.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.GlobalContext.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



val appModule = module {
    single<requestApi> {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestLoggingInterceptor()) // Ajoutez l'intercepteur de journalisation
            .build()

        Retrofit.Builder()
                .baseUrl("https://auto.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(requestApi::class.java)
    }
    single { GetVehiculeBySivUseCase() }
    factory<RequestLoggingInterceptor> { RequestLoggingInterceptor() }
    factory<MyRepository> { MyRepositoryImpl() }
    factory<RemoteDataSource> { RemoteDataSource() }
    viewModelOf(::UserViewModel)
}