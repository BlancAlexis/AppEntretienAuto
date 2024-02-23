package com.example.manageyourcar.dataLayer

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class CacheDataSource(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")
    private val Context.carDataStore by dataStore("car_prefs.json", CarPreferencesSerializer)

    private val USER_ID_KEY = intPreferencesKey("user_id")

    suspend fun saveUserCarList(carList: List<CarLocal>) {
        try {
            context.carDataStore.updateData { preferences ->
                preferences.copy(carCached = carList)
            }
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }

    suspend fun getUserCarList(): Ressource<List<CarLocal>> {
        try {
            val carListFlow: Flow<List<CarLocal>> = context.carDataStore.data.map { preferences ->
                preferences.carCached.toList()
            }
            return Ressource.Success(carListFlow.first())
        } catch (e: Exception) {
            e.printStackTrace()
            return Ressource.Error(message = "Error retrieving car list: $e")
        }
    }


    suspend fun getUserId(): Ressource<Int> {
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

    suspend fun putUserId(userId: Int): Ressource<Boolean> {
        return try {
            context.dataStore.edit { preferences ->
                preferences[USER_ID_KEY] = userId
            }
            Ressource.Success(true)
        } catch (e: Exception) {
            Ressource.Error(message = "Error storing user ID: $e")
        }
    }

    suspend fun resetCurrentUserId(): Ressource<Boolean> {
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