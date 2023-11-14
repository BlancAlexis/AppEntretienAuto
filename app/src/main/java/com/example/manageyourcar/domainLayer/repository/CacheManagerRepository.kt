package com.example.manageyourcar.domainLayer.repository

import android.app.Activity
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource

interface CacheManagerRepository {
    fun getCachedListUserCar()
    fun putListUserCarInCache()
    fun putUserId(activity: Activity, userId: Int): Ressource<Boolean>
    fun getUserId(activity: Activity): Ressource<Int>
}