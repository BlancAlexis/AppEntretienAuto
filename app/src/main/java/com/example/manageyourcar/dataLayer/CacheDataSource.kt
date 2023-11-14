package com.example.manageyourcar.dataLayer

import android.app.Activity
import android.content.Context
import com.example.manageyourcar.R
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.dsl.koinApplication

class CacheDataSource  {
    fun getUserId(activity: Activity): Ressource<Int> {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
            ?: return Ressource.Error(message = "Impossible de récupérer l'userID")
        val highScore = sharedPref.getInt(activity.getString(R.string.user_id), 0)
        if (highScore == 0) {
            return Ressource.Error(message = "Problème lors de la lecture")
        }
        return Ressource.Success(highScore)
    }

    fun putUserId(activity: Activity, userId: Int): Ressource<Boolean> {
        try {
            val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putInt(activity.getString(com.example.manageyourcar.R.string.user_id), userId)
                apply()
            }
        } catch (e: Error) {
            return Ressource.Error(message = "Impossible de récupérer l'userID")
        }
        return Ressource.Success(true)
    }

    fun getCachedListUserCar() {

    }

    fun putListUserCarInCache() {

    }
}