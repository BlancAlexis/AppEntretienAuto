package com.example.manageyourcar.dataLayer.dataLayerRoom

import android.app.Activity
import android.content.Context
import com.example.manageyourcar.R
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource

class cacheDataSource {
    fun getUserId(activity: Activity): Ressource<Int> {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
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