package com.example.manageyourcar.dataLayer

import android.content.Context
import android.content.SharedPreferences
import com.example.manageyourcar.R
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource


class CacheDataSource {
    fun getUserId(context: Context): Ressource<Int> {
        return try {
            val sharedPref = context.getSharedPreferences(
                context.getString(R.string.user_id),
                Context.MODE_PRIVATE
            )
                ?: return Ressource.Error(message = "Impossible de récupérer l'userID")
            val userID = sharedPref.getInt(context.getString(R.string.user_id), -1)
            if (userID == -1) {
                return Ressource.Error(message = "Problème lors de la lecture")
            }
            Ressource.Success(userID)
        } catch (e: Error) {
            Ressource.Error(e as Exception)
        }
    }

    fun putUserId(context: Context, userId: Int): Ressource<Boolean> {
        return try {
            val sharedPref = context.getSharedPreferences(
                context.getString(R.string.user_id),
                Context.MODE_PRIVATE
            )
            with(sharedPref.edit()) {
                putInt(context.getString(com.example.manageyourcar.R.string.user_id), userId)
                apply()
                return Ressource.Success(true)
            }
        } catch (e: Error) {
            return Ressource.Error(message = "Impossible de récupérer l'userID")
        }

    }

    fun getCachedListUserCar() {

    }

    fun putListUserCarInCache() {

    }

    fun resetCurrentUserId(context: Context): Ressource<Boolean> {
        return try {
            context.getSharedPreferences(context.getString(R.string.user_id), Context.MODE_PRIVATE).edit().clear().apply()
            Ressource.Success(true)
        }catch(e : Exception){
            Ressource.Error(message = "Impossible de supprimer l'userID $e")
        }
    }
}