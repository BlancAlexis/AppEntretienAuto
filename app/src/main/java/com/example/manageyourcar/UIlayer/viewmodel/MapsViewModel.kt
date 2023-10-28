package com.example.manageyourcar.UIlayer.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetCarRepairShopUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MapsViewModel : ViewModel(), KoinComponent {
    val getCarRepairShopUseCase by inject<GetCarRepairShopUseCase>()

    fun getCarRepairShop(longitude: Double, latitude: Double) {
        viewModelScope.launch {
            getCarRepairShopUseCase.getVehiculeByImmat(longitude, latitude).collect { result ->
                when (result) {
                    is Ressource.Error -> Log.e("c", "${result.message}")
                    is Ressource.Loading -> Log.e("c", "${result.message}")
                    is Ressource.Success -> Log.i("c", "${result.data.toString()}")
                }
            }
        }
    }
}