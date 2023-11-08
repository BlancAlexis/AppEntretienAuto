package com.example.manageyourcar.dataLayer

import android.app.Activity
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CacheManagerRepositoryImpl() : CacheManagerRepository, KoinComponent {
    private val dataSource by inject<CacheDataSource>()
    override fun getCachedListUserCar() {
        dataSource.getCachedListUserCar()
    }

    override fun putListUserCarInCache() {
        dataSource.putListUserCarInCache()
    }

    override fun putUserId(activity: Activity, userId: Int): Ressource<Boolean> {
        return dataSource.putUserId(activity, userId)
    }

    override fun getUserId(activity: Activity): Ressource<Int> {
        return dataSource.getUserId(activity)
    }
}