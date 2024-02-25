package com.example.manageyourcar.dataLayer.di

import com.example.manageyourcar.UIlayer.UIUtil
import com.example.manageyourcar.UIlayer.viewmodel.AddCarViewModel
import com.example.manageyourcar.UIlayer.viewmodel.AddMaintenanceViewModel
import com.example.manageyourcar.UIlayer.viewmodel.AddUserViewModel
import com.example.manageyourcar.UIlayer.viewmodel.ConnectObdViewModel
import com.example.manageyourcar.UIlayer.viewmodel.ListMaintenanceViewModel
import com.example.manageyourcar.UIlayer.viewmodel.LoginUserViewModel
import com.example.manageyourcar.UIlayer.viewmodel.UpdateCarMileageViewModel
import com.example.manageyourcar.UIlayer.viewmodel.ViewCarDetailsViewModel
import com.example.manageyourcar.dataLayer.AndroidBluetoothController
import com.example.manageyourcar.dataLayer.cacheManager.CacheDataSource
import com.example.manageyourcar.dataLayer.cacheManager.CacheManagerRepositoryImpl
import com.example.manageyourcar.dataLayer.ListenerInternet
import com.example.manageyourcar.dataLayer.cacheManager.CacheDataSourceImpl
import com.example.manageyourcar.dataLayer.dataLayerFirebase.MaintenanceFirestoreDataSourceImpl
import com.example.manageyourcar.dataLayer.dataLayerFirebase.CarFirestoreDataSourceImpl
import com.example.manageyourcar.dataLayer.dataLayerFirebase.CarFirestoreDataSource
import com.example.manageyourcar.dataLayer.dataLayerFirebase.CarFirestoreRepository
import com.example.manageyourcar.dataLayer.dataLayerFirebase.CarFirestoreRepositoryImpl
import com.example.manageyourcar.dataLayer.dataLayerFirebase.MaintenanceFirestoreDataSource
import com.example.manageyourcar.dataLayer.dataLayerFirebase.MaintenanceFirestoreRepository
import com.example.manageyourcar.dataLayer.dataLayerFirebase.MaintenanceFirestoreRepositoryImpl
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.RequestApiImmat
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.RequestApiSIV
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.placesApi
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryImpl.ApiCarImmatRepositoryImpl
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryImpl.ApiCarSIVRepositoryImpl
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.repositoryImpl.PlacesApiRepositoryImpl
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.RequestLoggingInterceptor
import com.example.manageyourcar.domainLayer.bluetooth.BluetoothController
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.repository.retrofit.ApiCarImmatRepository
import com.example.manageyourcar.domainLayer.repository.retrofit.ApiCarSIVRepository
import com.example.manageyourcar.domainLayer.repository.retrofit.PlacesApiRepository
import com.example.manageyourcar.domainLayer.useCaseBusiness.LoginUserUseCase
import com.example.manageyourcar.domainLayer.useCaseBusiness.LogoutUserUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetCarRepairShopUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkImmatUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.StoreUserCarUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.DeleteCarRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.UpsertCarMileageUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.AddCarMaintenanceUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.DeleteMaintenanceRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.user.AddUserRoomUseCase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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
    single<UIUtil> { UIUtil(androidContext()) }
    single { ListenerInternet(get(), androidApplication()) }
    factory<BluetoothController> { AndroidBluetoothController(get(), androidContext()) }
}

val mappersModule = module {
    single { com.example.manageyourcar.domainLayer.mappers.BluetoothDeviceMappers }
}


val repositoryModule = module {
    single<CacheManagerRepository> { CacheManagerRepositoryImpl(get()) }
    single<CacheDataSource> { CacheDataSourceImpl(androidContext()) }

}

val useCaseModule = module {
    factory { AddCarMaintenanceUseCase() }
    factory { StoreUserCarUseCase() }

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

val firebaseModule = module {
    single<FirebaseFirestore> { Firebase.firestore }
    factory<CarFirestoreDataSource> { CarFirestoreDataSourceImpl(get()) }
    single<CarFirestoreRepository> { CarFirestoreRepositoryImpl(get()) }

    single<MaintenanceFirestoreRepository> { MaintenanceFirestoreRepositoryImpl(get()) }
    single<MaintenanceFirestoreDataSource> { MaintenanceFirestoreDataSourceImpl(get()) }
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
