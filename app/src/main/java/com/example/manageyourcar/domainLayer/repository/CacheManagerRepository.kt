package com.example.manageyourcar.domainLayer.repository

import android.content.Context
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal

interface CacheManagerRepository {
    fun getUserCarList(): Ressource<List<CarLocal>>
    fun saveUserCarList(local: List<CarLocal>)
    fun putUserId(context: Context, userId: Int): Ressource<Boolean>
    fun getUserId(context: Context): Ressource<Int>
    fun resetUserId(context: Context): Ressource<Boolean>
}