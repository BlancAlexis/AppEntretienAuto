package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.manageyourcar.UIlayer.UIState.UpdateMileage
import com.example.manageyourcar.UIlayer.viewEvent.UIUtil
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import com.example.manageyourcar.domainLayer.useCaseRoom.car.UpsertCarMileageUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateCarMileageViewModel constructor(
    private val uiUtil: UIUtil
) : ViewModel(), KoinComponent {
    private val upsertCarMileageUseCase by inject<UpsertCarMileageUseCase>()
    private lateinit var navController: NavController
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO


    fun setNavController(view: NavController) {
        navController = view
    }

    private val _uiState = MutableStateFlow(UpdateMileage())
    val uiState = _uiState.asStateFlow()

    fun setUiStateCar(car: Car) {
        _uiState.update {
            it.copy(
                car = car
            )
        }
    }

    fun onEvent(event: UpdateCatEvent) {
        when (event) {
            UpdateCatEvent.OnUpdateMileage -> upsertCarMileage()
            is UpdateCatEvent.newMileage -> updateMileage(event)
        }
    }

    private fun updateMileage(event: UpdateCatEvent.newMileage) {
        _uiState.update {
            it.copy(
                newMileage = event.mileage
            )
        }
    }

    private fun upsertCarMileage() {
        viewModelScope.launch(ioDispatcher) {
            val car = _uiState.value.car
            if (uiState.value.newMileage?.toInt()!! < (car?.mileage?.last() ?: 0)) {
                uiUtil.displayToastSuspend("Vous ne pouvez pas ajouter un kilométrage inférieur au précédent")
                withContext(Dispatchers.Main) {
                    navController.popBackStack()
                }
                return@launch
            }

            if (car != null) {
                val updatedCar =
                    car.copy(mileage = car.mileage + (uiState.value.newMileage?.toInt() ?: 0))
                when (upsertCarMileageUseCase.updateCarMileage(updatedCar)) {
                    is Ressource.Error -> {
                        uiUtil.displayToastSuspend("Erreur lors de la mise à jour du kilométrage")
                        withContext(Dispatchers.Main) {
                            navController.popBackStack()
                        }
                    }

                    is Ressource.Success -> {
                        uiUtil.displayToastSuspend("Kilométrage mis à jour avec succès")
                        withContext(Dispatchers.Main) {
                            navController.popBackStack()
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}

sealed interface UpdateCatEvent {
    object OnUpdateMileage : UpdateCatEvent
    data class newMileage(val mileage: String) : UpdateCatEvent
}