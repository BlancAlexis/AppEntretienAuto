package com.example.manageyourcar.dataLayer

import android.content.Context
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import org.koin.core.component.KoinComponent

class CacheManagerRepositoryImpl(val dataSource: CacheDataSource) : CacheManagerRepository,
    KoinComponent {
    override fun getCachedListUserCar() {
        dataSource.getCachedListUserCar()
    }

    override fun putListUserCarInCache() {
        dataSource.putListUserCarInCache()
    }

    override fun putUserId(context: Context, userId: Int): Ressource<Boolean> {
        return dataSource.putUserId(context, userId)
    }

    override fun getUserId(context: Context): Ressource<Int> {
        return dataSource.getUserId(context)
    }
}