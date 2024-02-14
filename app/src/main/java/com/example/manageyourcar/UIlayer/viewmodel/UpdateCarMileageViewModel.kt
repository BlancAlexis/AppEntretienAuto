package com.example.manageyourcar.UIlayer.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.UIlayer.UIState.UpdateMileage
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.useCaseRoom.car.UpsertCarMileageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateCarMileageViewModel : ViewModel(), KoinComponent {
    private val upsertCarMileageUseCase by inject<UpsertCarMileageUseCase>()
    private lateinit var navController: NavController
    fun setNavController(view: NavController) {
        navController = view
    }

    private val _uiState = MutableStateFlow(UpdateMileage())
    val uiState = _uiState.asStateFlow()

    fun setCar(carLocal: com.example.manageyourcar.dataLayer.model.CarLocal) {
        _uiState.update {
            it.copy(
                carLocal = carLocal
            )
        }
    }

    fun onEvent(event: UpdateCatEvent) {
        when (event) {
            UpdateCatEvent.OnUpdateMileage -> upsertCarMileage()
            is UpdateCatEvent.newMileage -> _uiState.update {
                it.copy(
                    newMileage = event.mileage
                )
            }
        }
    }

    fun upsertCarMileage() {
        viewModelScope.launch(Dispatchers.IO) {
            val car = _uiState.value.carLocal
            if (car != null) {
                val updatedCar = car.copy(mileage = car.mileage + (uiState.value.newMileage?.toInt() ?: 0))
                when(upsertCarMileageUseCase.updateCarMileage(updatedCar)){
                    is Ressource.Error -> {
                        Toast.makeText(AppApplication.instance.applicationContext, "Erreur lors de la mise à jour du kilométrage", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                    is Ressource.Loading -> println("Loading")
                    is Ressource.Success -> {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(AppApplication.instance.applicationContext, "Kilométrage mis à jour avec succès", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }

                    }
                }
                //Géré les erreurs + newMileage inférieur à ancien
            }
        }
    }
}

sealed interface UpdateCatEvent {
    object OnUpdateMileage : UpdateCatEvent
    data class newMileage(val mileage: String) : UpdateCatEvent
}