package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.manageyourcar.UIlayer.composeView.UIState.UpdateMileage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class UpdateCarViewModel : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(UpdateMileage())
    val uiState = _uiState.asStateFlow()


    init {
    }
fun setCar(car: com.example.manageyourcar.dataLayer.model.Car) {
        _uiState.update {
            it.copy(
                car = car
            )
        }
    }
    fun onEvent(event: UpdateCatEvent) {
        when (event) {
            UpdateCatEvent.OnUpdateMileage -> TODO()
            is UpdateCatEvent.newMileage -> _uiState.update {
                it.copy(
                    newMileage = event.mileage
                )
            }
        }
    }
}
sealed interface UpdateCatEvent {
    object OnUpdateMileage :UpdateCatEvent
    data class newMileage(val mileage : String) : UpdateCatEvent
}