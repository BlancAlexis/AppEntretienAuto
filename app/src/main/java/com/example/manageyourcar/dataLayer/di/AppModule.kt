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
import com.example.manageyourcar.data.database.Database
import com.example.manageyourcar.domain.repository.CarRepository
import com.example.manageyourcar.data.repository.CarRepositoryImpl

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

    factory<RequestLoggingInterceptor> { RequestLoggingInterceptor() }
    factory<MyRepository> { MyRepositoryImpl() }
    factory<RemoteDataSource> { RemoteDataSource() }
}

  val viewModelModule = module {
    viewModelOf(::UserViewModel)
}
