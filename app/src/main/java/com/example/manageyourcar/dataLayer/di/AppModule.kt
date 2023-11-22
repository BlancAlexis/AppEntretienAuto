package com.example.manageyourcar.dataLayer.di

import androidx.room.Room
import com.example.manageyourcar.UIlayer.viewmodel.AddCarViewModel
import com.example.manageyourcar.UIlayer.viewmodel.AddUserViewModel
import com.example.manageyourcar.UIlayer.viewmodel.AddMaintenanceViewModel
import com.example.manageyourcar.UIlayer.viewmodel.LogUserViewModel
import com.example.manageyourcar.UIlayer.viewmodel.MapsViewModel
import com.example.manageyourcar.UIlayer.viewmodel.UserViewModel
import com.example.manageyourcar.UIlayer.viewmodel.ServicingViewModel
import com.example.manageyourcar.dataLayer.CacheDataSource
import com.example.manageyourcar.dataLayer.CacheManagerRepositoryImpl
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.garageApi
import com.example.manageyourcar.domainLayer.repository.retrofit.ApiCarImmatRepository
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryImpl.ApiCarImmatRepositoryImpl
import com.example.manageyourcar.domainLayer.repository.retrofit.ApiCarSIVRepository
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryImpl.ApiCarSIVRepositoryImpl
import com.example.manageyourcar.domainLayer.repository.retrofit.GarageRepository
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryImpl.GarageRepositoryImpl
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.requestApiImmat
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.requestApiSIV
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.RequestLoggingInterceptor
import com.example.manageyourcar.dataLayer.dataLayerRoom.database.Database
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import com.example.manageyourcar.domainLayer.repository.room.ServicingRepository
import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import com.example.manageyourcar.dataLayer.dataLayerRoom.repositoryImpl.CarRepositoryImpl
import com.example.manageyourcar.dataLayer.dataLayerRoom.repositoryImpl.ServicingRepositoryImpl
import com.example.manageyourcar.dataLayer.dataLayerRoom.repositoryImpl.UserRepositoryImpl
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.useCaseBusiness.LoginUserUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetCarRepairShopUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkImmatUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.AddCarToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.DeleteCarToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetCarFromRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetCarsFromRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.UpdateCarToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.AddCarMaintenanceUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.DeleteServicingToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.GetAllServicingFromRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.GetAllUserMaintenanceUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.GetServicingFromRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.UpdateServicingToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.user.AddUserToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.user.DeleteUserToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.user.GetUserFromRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.user.GetUsersFromRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.user.UpdateUserToRoomUseCase
import com.example.manageyourcar.domainLayer.utils.SmsSender
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
            retrofitModule,
            mappersModule,
            utils
        )
    )
}

val utils = module {
    single{SmsSender}
}

val mappersModule = module {
    single{ com.example.manageyourcar.domainLayer.mappers.UserMappers }
    single{ com.example.manageyourcar.domainLayer.mappers.CarMappers }
    single{ com.example.manageyourcar.domainLayer.mappers.MaintenanceMappers }
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
        get<Database>().getServicingDAO()
    }
}

val repositoryModule = module {

    single<CacheManagerRepository> { CacheManagerRepositoryImpl(get()) }
    single { CacheDataSource() }
    single<CarRepository> { CarRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<ServicingRepository> { ServicingRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory { AddCarMaintenanceUseCase() }

    factory { GetAllUserMaintenanceUseCase() }
    factory { GetUserCarsUseCase() }
    factory { LoginUserUseCase() }
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

    factory { GetServicingFromRoomUseCase() }
    factory { GetAllServicingFromRoomUseCase() }
    factory { UpdateServicingToRoomUseCase() }
    factory { DeleteServicingToRoomUseCase() }

    factory { GetVehiculeByNetworkUseCase() }
    factory { GetCarRepairShopUseCase() }
    factory { GetVehiculeByNetworkImmatUseCase() }

}

val retrofitModule = module {
    single<garageApi> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestLoggingInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/place/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(garageApi::class.java)
    }

    factory<GarageRepository> { GarageRepositoryImpl() }


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
    viewModelOf(::MapsViewModel)
    viewModelOf(::ServicingViewModel)
    viewModelOf(::AddMaintenanceViewModel)

}
