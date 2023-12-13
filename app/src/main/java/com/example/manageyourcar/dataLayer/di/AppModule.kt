package com.example.manageyourcar.dataLayer.di

import androidx.room.Room
import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.UIlayer.viewmodel.AddCarViewModel
import com.example.manageyourcar.UIlayer.viewmodel.AddMaintenanceViewModel
import com.example.manageyourcar.UIlayer.viewmodel.AddUserViewModel
import com.example.manageyourcar.UIlayer.viewmodel.BluetoothViewModel
import com.example.manageyourcar.UIlayer.viewmodel.LogUserViewModel
import com.example.manageyourcar.UIlayer.viewmodel.MapsViewModel
import com.example.manageyourcar.UIlayer.viewmodel.OBDViewModel
import com.example.manageyourcar.UIlayer.viewmodel.ListMaintenanceViewModel
import com.example.manageyourcar.dataLayer.CacheDataSource
import com.example.manageyourcar.dataLayer.CacheManagerRepositoryImpl
import com.example.manageyourcar.dataLayer.ListenerInternet
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.garageApi
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryImpl.ApiCarImmatRepositoryImpl
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryImpl.ApiCarSIVRepositoryImpl
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
import com.example.manageyourcar.domainLayer.bluetooth.BluetoothController
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.repository.retrofit.ApiCarImmatRepository
import com.example.manageyourcar.domainLayer.repository.retrofit.ApiCarSIVRepository
import com.example.manageyourcar.domainLayer.repository.retrofit.GarageRepository
import com.example.manageyourcar.domainLayer.useCaseBusiness.LoginUserUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetCarRepairShopUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkImmatUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.DeleteCarRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.AddCarMaintenanceUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.DeleteMaintenanceRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.GetAllUserMaintenanceUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.UpdateMaintenanceRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.user.AddUserRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.user.DeleteUserRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.user.GetUserRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.user.GetUsersRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.user.UpdateUserRoomUseCase
import com.example.manageyourcar.domainLayer.utils.SmsSender
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.example.manageyourcar.dataLayer.AndroidBluetoothController
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
            utils,
            firebaseModule
        )
    )
}

val utils = module {
    single { SmsSender }
    factory<BluetoothController> { AndroidBluetoothController(AppApplication.instance) }
}

val firebaseModule = module {
    single { Firebase.database }
}
val mappersModule = module {
    single { com.example.manageyourcar.domainLayer.mappers.UserMappers }
    single { com.example.manageyourcar.domainLayer.mappers.BluetoothDeviceMappers }
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
    single { ListenerInternet() }
    factory { AddCarMaintenanceUseCase() }

    factory { GetAllUserMaintenanceUseCase() }
    factory { GetUserCarsUseCase() }
    factory { LoginUserUseCase() }
    factory { DeleteCarRoomUseCase() }

    factory { AddUserRoomUseCase() }
    factory { GetUserRoomUseCase() }
    factory { GetUsersRoomUseCase() }
    factory { UpdateUserRoomUseCase() }
    factory { DeleteUserRoomUseCase() }

    factory { UpdateMaintenanceRoomUseCase() }
    factory { DeleteMaintenanceRoomUseCase() }

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
    viewModelOf(::AddUserViewModel)
    viewModelOf(::LogUserViewModel)
    viewModelOf(::AddCarViewModel)
    viewModelOf(::MapsViewModel)
    viewModelOf(::ListMaintenanceViewModel)
    viewModelOf(::AddMaintenanceViewModel)
    viewModelOf(::OBDViewModel)
    viewModelOf(::BluetoothViewModel)

}
