package com.example.manageyourcar.domainLayer.repository

import android.content.Context
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource

interface CacheManagerRepository {
    fun getCachedListUserCar()
    fun putListUserCarInCache()
    fun putUserId(context: Context, userId: Int): Ressource<Boolean>
    fun getUserId(context: Context): Ressource<Int>
    fun resetUserId(context: Context): Ressource<Boolean>
}