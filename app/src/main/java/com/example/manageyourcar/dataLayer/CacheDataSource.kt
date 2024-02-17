package com.example.manageyourcar.dataLayer

import android.content.Context
import com.example.manageyourcar.R
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal


class CacheDataSource {
    private var userCarList: List<CarLocal> = emptyList()
    fun getUserCarList(): List<CarLocal> {
        return userCarList
    }
    fun setUserCarList(local: CarLocal){
        userCarList = userCarList + local

    }
    fun getUserId(context: Context): Ressource<Int> {
        return try {
            val sharedPref = context.getSharedPreferences(
                context.getString(R.string.user_id),
                Context.MODE_PRIVATE
            ) ?: return Ressource.Error(message = "Impossible de récupérer l'userID")
            val userID = sharedPref.getInt(context.getString(R.string.user_id), -1)
            if (userID == -1) {
                return Ressource.Error(message = "Problème lors de la lecture")
            }
            Ressource.Success(userID)
        } catch (e: Error) {
            Ressource.Error(message = "Erreur lors de la récupération de l'userID")
        }
    }

    fun putUserId(context: Context, userId: Int): Ressource<Boolean> {
        return try {
            val sharedPref = context.getSharedPreferences(
                context.getString(R.string.user_id),
                Context.MODE_PRIVATE
            )
            with(sharedPref.edit()) {
                putInt(context.getString(R.string.user_id), userId)
                apply()
                return Ressource.Success(true)
            }
        } catch (e: Error) {
            return Ressource.Error(message = "Erreur lors de l'écriture de l'userID")
        }

    }

    fun resetCurrentUserId(context: Context): Ressource<Boolean> {
        return try {
            context.getSharedPreferences(context.getString(R.string.user_id), Context.MODE_PRIVATE)
                .edit().clear().apply()
            Ressource.Success(true)
        } catch (e: Exception) {
            Ressource.Error(message = "Impossible de supprimer l'userID $e")
        }
    }
}