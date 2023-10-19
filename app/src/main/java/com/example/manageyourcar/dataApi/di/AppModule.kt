package com.example.manageyourcar.dataApi.di

import androidx.room.Room
import com.example.manageyourcar.dataApi.dataSource.RemoteDataSource
import com.example.manageyourcar.dataApi.util.RequestLoggingInterceptor
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import okhttp3.OkHttpClient
import com.example.manageyourcar.UIlayer.viewmodel.UserViewModel
import com.example.manageyourcar.dataRoom.database.Database
import com.example.manageyourcar.dataRoom.repository.CarRepository
import com.example.manageyourcar.dataRoom.repositoryImpl.CarRepositoryImpl
import com.example.manageyourcar.dataApi.repositoryRetrofit.ApiCarSIVRepository
import com.example.manageyourcar.dataApi.repositoryRetrofit.ApiCarSIVRepositoryImpl
import com.example.manageyourcar.dataApi.requestApiSIV

import com.example.manageyourcar.domainLayer.useCaseRoom.AddCarToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.GetCarFromRoomUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



fun injectFeature() = loadFeature


    private val loadFeature by lazy {
        loadKoinModules(
            listOf(
                databaseModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
                retrofitModule
            )
        )
    }

    val databaseModule = module {
        single {
            Room.databaseBuilder(
                get(),
                Database::class.java,
                "Database_Manage"
            ).build()
        }
        single{
            get<Database>().getCarDAO()
        }
    }

    val repositoryModule = module {
        single<CarRepository> { CarRepositoryImpl(get()) }
    }

val useCaseModule = module {
    factory { AddCarToRoomUseCase() }
    factory { GetCarFromRoomUseCase() }
    factory { GetVehiculeByNetworkUseCase() }

}

val retrofitModule = module {
    single<requestApiSIV> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestLoggingInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl("https://apimobile.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(requestApiSIV::class.java)
    }

    factory<RequestLoggingInterceptor> { RequestLoggingInterceptor() }
    factory<ApiCarSIVRepository> { ApiCarSIVRepositoryImpl() }
    factory<RemoteDataSource> { RemoteDataSource() }
}

  val viewModelModule = module {
    viewModelOf(::UserViewModel)
}