package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.UIlayer.UIState.UpdateMileage
import com.example.manageyourcar.domainLayer.useCaseRoom.car.UpsertCarMileageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateCarMileageViewModel : ViewModel(), KoinComponent {
    private val upsertCarMileageUseCase by inject<UpsertCarMileageUseCase>()

    private val _uiState = MutableStateFlow(UpdateMileage())
    val uiState = _uiState.asStateFlow()

    fun setCar(car: com.example.manageyourcar.dataLayer.model.Car) {
        _uiState.update {
            it.copy(
                car = car
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
            val car = _uiState.value.car
            if (car != null) {
                val updatedCar = car.copy(mileage = car.mileage + listOf(12346))
                println(updatedCar.mileage)
                upsertCarMileageUseCase.updateCarMileage(updatedCar)
                println("tipar")
            }

        }
    }
}

sealed interface UpdateCatEvent {
    object OnUpdateMileage : UpdateCatEvent
    data class newMileage(val mileage: String) : UpdateCatEvent
}