package com.example.manageyourcar.dataLayer.cacheManager

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


class CacheDataSourceImpl(private val context: Context) : CacheDataSource{
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")
    private val Context.carDataStore by dataStore("car_prefs.json", CarPreferencesSerializer)

    private val USER_ID_KEY = intPreferencesKey("user_id")

    override suspend fun saveUserCarList(carList: List<CarLocal>) {
        try {
            context.carDataStore.updateData { preferences ->
                preferences.copy(carCached = carList)
            }
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }

    override suspend fun getUserCarList(): Ressource<List<CarLocal>> {
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
}

interface CacheDataSource {
    suspend fun saveUserCarList(carList: List<CarLocal>)

    suspend fun getUserCarList(): Ressource<List<CarLocal>>
}
