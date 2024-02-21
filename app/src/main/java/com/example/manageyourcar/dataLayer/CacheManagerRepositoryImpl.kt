package com.example.manageyourcar.dataLayer

import android.content.Context
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import org.koin.core.component.KoinComponent


class CacheManagerRepositoryImpl(private val dataSource: CacheDataSource) : CacheManagerRepository,
    KoinComponent {
    override fun getUserCarList(): Ressource<List<CarLocal>> {
        return dataSource.getUserCarList()
    }

    override fun saveUserCarList(local: List<CarLocal>) {
        dataSource.setUserCarList(local)
    }


    override fun putUserId(userId: Int): Ressource<Boolean> {
        return dataSource.putUserId(userId)
    }

    override fun getUserId(): Ressource<Int> {
        return dataSource.getUserId()
    }

    override fun resetUserId(): Ressource<Boolean> {
        return dataSource.resetCurrentUserId()
    }
}