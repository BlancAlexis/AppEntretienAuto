package com.example.manageyourcar.domainLayer.repository.retrofit

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.dataSource.RemoteDataSource
import com.example.manageyourcar.dataLayer.dataLayerRoom.cacheDataSource
import com.example.manageyourcar.domainLayer.repository.cacheRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class cacheRepoImpl : cacheRepo, KoinComponent {
    private val cacheDataSource by inject<cacheDataSource>()

    override fun getUserID(): Int {
        TODO("Not yet implemented")
    }

    override fun putUserID() {
        TODO("Not yet implemented")
    }
}