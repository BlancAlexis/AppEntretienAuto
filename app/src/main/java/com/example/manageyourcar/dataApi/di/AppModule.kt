package com.example.manageyourcar.dataApi.di

import androidx.room.Room
import com.example.manageyourcar.UIlayer.viewmodel.AddCarViewModel
import com.example.manageyourcar.UIlayer.viewmodel.AddUserViewModel
import com.example.manageyourcar.UIlayer.viewmodel.LogUserViewModel
import com.example.manageyourcar.UIlayer.viewmodel.UserViewModel
import com.example.manageyourcar.dataApi.dataSource.RemoteDataSource
import com.example.manageyourcar.dataApi.repositoryRetrofit.ApiCarImmatRepository
import com.example.manageyourcar.dataApi.repositoryRetrofit.ApiCarImmatRepositoryImpl
import com.example.manageyourcar.dataApi.repositoryRetrofit.ApiCarSIVRepository
import com.example.manageyourcar.dataApi.repositoryRetrofit.ApiCarSIVRepositoryImpl
import com.example.manageyourcar.dataApi.requestApiImmat
import com.example.manageyourcar.dataApi.requestApiSIV
import com.example.manageyourcar.dataApi.util.RequestLoggingInterceptor
import com.example.manageyourcar.dataRoom.database.Database
import com.example.manageyourcar.dataRoom.repository.CarRepository
import com.example.manageyourcar.dataRoom.repository.EntretienRepository
import com.example.manageyourcar.dataRoom.repository.UserRepository
import com.example.manageyourcar.dataRoom.repositoryImpl.CarRepositoryImpl
import com.example.manageyourcar.dataRoom.repositoryImpl.EntretienRepositoryImpl
import com.example.manageyourcar.dataRoom.repositoryImpl.UserRepositoryImpl
import com.example.manageyourcar.dataRoom.useCase.car.AddCarToRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.car.DeleteCarToRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.car.GetCarFromRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.car.GetCarsFromRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.car.UpdateCarToRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.entretien.AddEntretienToRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.entretien.DeleteEntretienToRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.entretien.GetEntretienFromRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.entretien.GetEntretiensFromRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.entretien.UpdateEntretienToRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.user.AddUserToRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.user.DeleteUserToRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.user.GetUserFromRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.user.GetUsersFromRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.user.UpdateUserToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkImmatUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import okhttp3.OkHttpClient
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
    single {
        get<Database>().getCarDAO()
    }
    single {
        get<Database>().getUserDAO()
    }
    single {
        get<Database>().getEntretienDAO()
    }
}

val repositoryModule = module {
    single<CarRepository> { CarRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<EntretienRepository> { EntretienRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory { AddCarToRoomUseCase() }
    factory { GetCarFromRoomUseCase() }
    factory { GetCarsFromRoomUseCase() }
    factory { UpdateCarToRoomUseCase() }
    factory { DeleteCarToRoomUseCase() }

    factory { AddUserToRoomUseCase() }
    factory { GetUserFromRoomUseCase() }
    factory { GetUsersFromRoomUseCase() }
    factory { UpdateUserToRoomUseCase() }
    factory { DeleteUserToRoomUseCase() }

    factory { AddEntretienToRoomUseCase() }
    factory { GetEntretienFromRoomUseCase() }
    factory { GetEntretiensFromRoomUseCase() }
    factory { UpdateEntretienToRoomUseCase() }
    factory { DeleteEntretienToRoomUseCase() }

    factory { GetVehiculeByNetworkUseCase() }
    factory { GetVehiculeByNetworkImmatUseCase() }

}

val retrofitModule = module {
    single<requestApiSIV> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestLoggingInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl("https://auto.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(requestApiSIV::class.java)
    }

    factory<RequestLoggingInterceptor> { RequestLoggingInterceptor() }
    factory<ApiCarSIVRepository> { ApiCarSIVRepositoryImpl() }
    factory<RemoteDataSource> { RemoteDataSource() }

    single<requestApiImmat> {
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

    factory<ApiCarImmatRepository> { ApiCarImmatRepositoryImpl() }
}

val viewModelModule = module {
    viewModelOf(::UserViewModel)
    viewModelOf(::AddUserViewModel)
    viewModelOf(::LogUserViewModel)
    viewModelOf(::AddCarViewModel)
}
