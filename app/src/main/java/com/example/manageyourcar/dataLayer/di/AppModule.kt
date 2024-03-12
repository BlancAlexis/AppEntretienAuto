package com.example.manageyourcar.dataLayer.di

import androidx.room.Room
import com.example.manageyourcar.UIlayer.viewEvent.ListenerInternet
import com.example.manageyourcar.UIlayer.viewEvent.UIUtil
import com.example.manageyourcar.UIlayer.viewmodel.AddCarViewModel
import com.example.manageyourcar.UIlayer.viewmodel.AddMaintenanceViewModel
import com.example.manageyourcar.UIlayer.viewmodel.AddUserViewModel
import com.example.manageyourcar.UIlayer.viewmodel.ConnectObdViewModel
import com.example.manageyourcar.UIlayer.viewmodel.ListMaintenanceViewModel
import com.example.manageyourcar.UIlayer.viewmodel.LoginUserViewModel
import com.example.manageyourcar.UIlayer.viewmodel.UpdateCarMileageViewModel
import com.example.manageyourcar.UIlayer.viewmodel.ViewCarDetailsViewModel
import com.example.manageyourcar.dataLayer.cache.CacheDataSource
import com.example.manageyourcar.dataLayer.cache.CacheManagerRepositoryImpl
import com.example.manageyourcar.dataLayer.retrofit.RequestApiImmat
import com.example.manageyourcar.dataLayer.retrofit.RequestApiSIV
import com.example.manageyourcar.dataLayer.retrofit.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.retrofit.placesApi
import com.example.manageyourcar.dataLayer.retrofit.repositoryImpl.ApiCarImmatRepositoryImpl
import com.example.manageyourcar.dataLayer.retrofit.repositoryImpl.ApiCarSIVRepositoryImpl
import com.example.manageyourcar.dataLayer.retrofit.repositoryImpl.PlacesApiRepositoryImpl
import com.example.manageyourcar.dataLayer.retrofit.util.RequestLoggingInterceptor
import com.example.manageyourcar.dataLayer.room.database.Database
import com.example.manageyourcar.dataLayer.room.repositoryImpl.CarRepositoryImpl
import com.example.manageyourcar.dataLayer.room.repositoryImpl.ServicingRepositoryImpl
import com.example.manageyourcar.dataLayer.room.repositoryImpl.UserRepositoryImpl
import com.example.manageyourcar.domainLayer.bluetooth.AndroidBluetoothController
import com.example.manageyourcar.domainLayer.bluetooth.BluetoothController
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.repository.retrofit.ApiCarImmatRepository
import com.example.manageyourcar.domainLayer.repository.retrofit.ApiCarSIVRepository
import com.example.manageyourcar.domainLayer.repository.retrofit.PlacesApiRepository
import com.example.manageyourcar.domainLayer.repository.room.CarRepository
import com.example.manageyourcar.domainLayer.repository.room.ServicingRepository
import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import com.example.manageyourcar.domainLayer.useCaseBusiness.LoginUserUseCase
import com.example.manageyourcar.domainLayer.useCaseBusiness.LogoutUserUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetCarRepairShopUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkImmatUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.AddCarRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.DeleteCarRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.UpsertCarMileageUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.AddCarMaintenanceUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.DeleteMaintenanceRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.GetAllUserMaintenanceUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.user.AddUserRoomUseCase
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
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
        )
    )
}

val utils = module {
    single<UIUtil> { UIUtil(androidContext()) }
    single { ListenerInternet(get(), androidApplication()) }
    factory<BluetoothController> { AndroidBluetoothController(get(), androidContext()) }
}

val mappersModule = module {
    single { com.example.manageyourcar.domainLayer.mappers.UserMappers }
    single { com.example.manageyourcar.domainLayer.mappers.BluetoothDeviceMappers }
    single { com.example.manageyourcar.domainLayer.mappers.CarMappers }
    single { com.example.manageyourcar.domainLayer.mappers.MaintenanceMappers }
}
val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            Database::class.java,
            "Database_Manage"
        ).fallbackToDestructiveMigration()
            .build()
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
    single<CarRepository> { CarRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<ServicingRepository> { ServicingRepositoryImpl(get()) }
    single<CacheDataSource> { CacheDataSource(androidContext()) }
}

val useCaseModule = module {
    factory { AddCarMaintenanceUseCase() }
    factory { AddCarRoomUseCase() }

    factory { GetAllUserMaintenanceUseCase() }
    factory { GetUserCarsUseCase() }
    factory { LoginUserUseCase() }
    factory { LogoutUserUseCase() }
    factory { DeleteCarRoomUseCase() }

    factory { AddUserRoomUseCase() }
    factory { UpsertCarMileageUseCase() }

    factory { DeleteMaintenanceRoomUseCase() }

    factory { GetVehiculeByNetworkUseCase() }
    factory { GetCarRepairShopUseCase() }
    factory { GetVehiculeByNetworkImmatUseCase() }

}

val retrofitModule = module {
    single<placesApi> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestLoggingInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/place/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(placesApi::class.java)
    }

    factory<PlacesApiRepository> { PlacesApiRepositoryImpl() }


    single<RequestApiSIV> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestLoggingInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl("https://auto.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RequestApiSIV::class.java)
    }

    factory<RequestLoggingInterceptor> { RequestLoggingInterceptor() }
    factory<ApiCarSIVRepository> { ApiCarSIVRepositoryImpl() }
    factory<RemoteDataSource> { RemoteDataSource() }

    single<RequestApiImmat> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestLoggingInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl("https://apimobile.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RequestApiImmat::class.java)
    }

    factory<ApiCarImmatRepository> { ApiCarImmatRepositoryImpl() }
}

val viewModelModule = module {
    viewModelOf(::AddUserViewModel)
    viewModelOf(::LoginUserViewModel)
    viewModelOf(::AddCarViewModel)
    viewModelOf(::ListMaintenanceViewModel)
    viewModelOf(::AddMaintenanceViewModel)
    viewModelOf(::ConnectObdViewModel)
    viewModelOf(::ViewCarDetailsViewModel)
    viewModelOf(::UpdateCarMileageViewModel)
}
