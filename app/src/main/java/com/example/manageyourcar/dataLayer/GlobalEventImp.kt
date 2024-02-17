package com.example.manageyourcar.dataLayer

import android.location.Location
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class GlobalEventImp constructor( private val cacheManagerRepository: CacheManagerRepository): GlobalEvent, KoinComponent {
    override fun getEvent(): String {
        return "Event"
    }

    override fun getUserCarList(key: String) {
 this.getUserCarList("")
    }

    override suspend fun saveUserCarList(carList: List<CarLocal>) {
        withContext(Dispatchers.Default) {
            this@GlobalEventImp.saveUserCarList(carList)

        }
    }

    override fun onInternetInternetLost() {
    }

    override fun onLocationChanged(location: Location) {
    }
}