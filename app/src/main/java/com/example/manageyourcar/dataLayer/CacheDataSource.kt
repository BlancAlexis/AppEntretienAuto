package com.example.manageyourcar.dataLayer

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.manageyourcar.R
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class CacheDataSource {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

    val USER_ID_KEY = intPreferencesKey("user_id")

    private var userCarList: List<CarLocal> = emptyList()
    fun getUserCarList(): Ressource<List<CarLocal>> {
        try {
            return Ressource.Success(userCarList)
        } catch (e: Exception) {
            return Ressource.Error(message = "Erreur lors de la récupération de la liste de voiture")
        }
    }

    fun setUserCarList(local: List<CarLocal>) {
        userCarList = local

    }

    fun getUserId(): Ressource<Int> {
        return try {
            val userIdFlow: Flow<Int> = context.dataStore.data.map { preferences ->
                preferences[USER_ID_KEY] ?: -1
            }
            val userId = userIdFlow.first()
            if (userId == -1) {
                Ressource.Error(message = "Problem reading user ID")
            } else {
                Ressource.Success(userId)
            }
        } catch (e: Exception) {
            Ressource.Error(message = "Error retrieving user ID: $e")
        }
    }

    fun putUserId(userId: Int): Ressource<Boolean> {
        return try {
            context.dataStore.edit { preferences ->
                preferences[USER_ID_KEY] = userId
            }
            Ressource.Success(true)
        } catch (e: Exception) {
            Ressource.Error(message = "Error storing user ID: $e")
        }
    }

    suspend fun resetCurrentUserId(context: Context): Ressource<Boolean> {
        return try {
            context.dataStore.edit { preferences ->
                preferences.clear()
            }
            Ressource.Success(true)
        } catch (e: Exception) {
            Ressource.Error(message = "Error clearing user ID: $e")
        }
    }
}