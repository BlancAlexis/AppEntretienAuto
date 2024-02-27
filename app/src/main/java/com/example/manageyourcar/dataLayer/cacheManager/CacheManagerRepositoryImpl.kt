package com.example.manageyourcar.dataLayer.cacheManager

import com.example.manageyourcar.dataLayer.cacheManager.CacheDataSource
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import org.koin.core.component.KoinComponent


class CacheManagerRepositoryImpl(private val dataSource: CacheDataSource) : CacheManagerRepository {
    override suspend fun getUserCarList(): Ressource<List<CarLocal>> {
        return dataSource.getUserCarList()
    }

    override suspend fun saveUserCarList(local: List<CarLocal>) {
        dataSource.saveUserCarList(local)
    }
}