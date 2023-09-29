package com.example.manageyourcar.dataLayer.di

import androidx.room.Room
import com.example.manageyourcar.dataLayer.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.repository.MyRepository
import com.example.manageyourcar.dataLayer.repository.MyRepositoryImpl
import com.example.manageyourcar.dataLayer.requestApi
import com.example.manageyourcar.dataLayer.util.RequestLoggingInterceptor
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import okhttp3.OkHttpClient
import com.example.manageyourcar.UIlayer.viewmodel.UserViewModel
import com.example.manageyourcar.dataLayer.dataSource.room.CarDao
import com.example.manageyourcar.dataLayer.dataSource.room.Database
import com.example.manageyourcar.dataLayer.repository.room.car.CarRepository
import com.example.manageyourcar.dataLayer.repository.room.car.CarRepositoryImpl
import com.example.manageyourcar.dataLayer.service.room.car.CarDatabaseService
import com.example.manageyourcar.dataLayer.service.room.car.CarDatabaseServiceImpl
import com.example.manageyourcar.domainLayer.useCaseRoom.AddCarToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.GetCarFromRoomUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



val appModule = module {
    //Database
    single {
        Room.databaseBuilder(
            androidApplication(),
            Database::class.java,
            "Database_Manage"
        ).build()
    }
    single<CarDao> {
        val database = get<Database>()
        database.getCarDAO()
    }
single<CarRepository> { CarRepositoryImpl() }
    single { AddCarToRoomUseCase() }
    single { GetCarFromRoomUseCase() }
    factory<CarDatabaseService> { CarDatabaseServiceImpl() }

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