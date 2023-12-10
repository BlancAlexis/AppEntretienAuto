package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.UIlayer.composeView.UIState.AddVehiculeMaintenanceUiState
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.dataLayer.model.MaintenanceService
import com.example.manageyourcar.dataLayer.model.MaintenanceServiceType
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.AddCarMaintenanceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Date

class AddMaintenanceViewModel : ViewModel(), KoinComponent {
    private val getUserCarsUseCase by inject<GetUserCarsUseCase>()

    private val _uiState = MutableStateFlow(AddVehiculeMaintenanceUiState())
    val uiState = _uiState.asStateFlow()

    private val addCarMaintenanceUseCase by inject<AddCarMaintenanceUseCase>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserCarsUseCase.invoke().collect{ result ->
                when(result){
                    is Ressource.Error -> TODO()
                    is Ressource.Loading -> TODO()
                    is Ressource.Success -> result.data?.let { updateListCar(it) }
                }
            }
        }
}
    private fun updateListCar(data: List<Car>) {
        _uiState.update {
            it.copy(
                listCars = data
            )
        }
    }

    fun onEvent(event: onMaintenanceEvent) {
        when (event) {
            is onMaintenanceEvent.onCarChanged -> OnCarChanged(event.newValue)
            is onMaintenanceEvent.onMaintenanceChanged -> OnMaintenanceChanged(event.newValue)
            is onMaintenanceEvent.onMileageChanged -> OnMileageChanged(event.newValue)
            is onMaintenanceEvent.onDateChanged -> OnDateChanged(Date(event.newDate))
            onMaintenanceEvent.onValidatePressed -> TODO()
        }

    }

    private fun OnCarChanged(newValue: Car) {
        _uiState.update {
            it.copy(
                selectedCar = newValue
            )
        }
    }

    private fun OnMaintenanceChanged(newValue: MaintenanceServiceType) {
        _uiState.update {
            it.copy(
                selectedMaintenance = newValue
            )
        }
    }

    private fun OnMileageChanged(newValue: Int) {
        _uiState.update {
            it.copy(
                mileage = newValue
            )
        }
    }

    private fun OnDateChanged(newDate: Date) {
        _uiState.update {
            it.copy(
                date = newDate
            )
        }
    }


}

sealed interface onMaintenanceEvent {
    object onValidatePressed : onMaintenanceEvent
    data class onDateChanged(val newDate: String) : onMaintenanceEvent
    data class onMileageChanged(val newValue: Int) : onMaintenanceEvent
    data class onMaintenanceChanged(val newValue: MaintenanceServiceType) : onMaintenanceEvent
    data class onCarChanged(val newValue: Car) : onMaintenanceEvent

}

