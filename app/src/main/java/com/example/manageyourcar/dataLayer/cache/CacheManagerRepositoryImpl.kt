package com.example.manageyourcar.dataLayer.cache

import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import org.koin.core.component.KoinComponent


class CacheManagerRepositoryImpl(private val dataSource: CacheDataSource) : CacheManagerRepository,
    KoinComponent {
    override suspend fun getUserCarList(): Ressource<List<Car>> {
        return dataSource.getUserCarList()
    }

    override suspend fun saveUserCarList(local: List<Car>) {
        dataSource.saveUserCarList(local)
    }


    override suspend fun putUserId(userId: Int): Ressource<Boolean> {
        return dataSource.putUserId(userId)
    }

    override suspend fun getUserId(): Ressource<Int> {
        return dataSource.getUserId()
    }

    override suspend fun resetUserId(): Ressource<Boolean> {
        return dataSource.resetCurrentUserId()
    }
}