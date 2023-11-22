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
import java.time.Instant
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
                    is Ressource.Success -> updateListCar(result.data)
                }
            }
        }
}

/*    init {
        viewModelScope.launch(Dispatchers.IO) {
            addCarToRoomUseCase.addCarToRoom(Car(carID = null, "AA-123-AA", "Clio", Date(12), "d", "1.5 DCI", "d", 5, 5, 5, 5, 1))
            addCarMaintenanceUseCase.addMainntenanceOperation(Entretien(1, 1,12,12, Date.from(
                Instant.now()),MaintenanceService.Pneus()))
            getAllUserMaintenanceUseCase.invoke().collect { result ->
                when (result) {
                    is Ressource.Error ->{}
                    is Ressource.Loading -> listLoad()
                    is Ressource.Success -> listLoading(result.data)
                }
            }
        }
    }*/

    private fun updateListCar(data: List<Car>?) {
        _uiState.update {
            it.copy(
                listCars = data!!
            )
        }
    }

    fun onEvent(event: onMaintenanceEvent) {
        when (event) {
            is onMaintenanceEvent.onCarChanged -> OnCarChanged(event.newValue)
            is onMaintenanceEvent.onMaintenanceChanged -> OnMaintenanceChanged(event.newValue)
            is onMaintenanceEvent.onMileageChanged -> OnMileageChanged(event.newValue)
            is onMaintenanceEvent.onDateChanged -> OnDateChanged(Date(event.newDate))
            is onMaintenanceEvent.onValidatePressed -> addMaintenance()
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

    private fun addMaintenance() {
        viewModelScope.launch(Dispatchers.IO) {
            addCarMaintenanceUseCase.addMainntenanceOperation(
                Entretien(
                    userID = null,
                    carID = uiState.value.selectedCar?.carID!!,
                    uiState.value.mileage,
                    uiState.value.price,
                    uiState.value.date!!,
                    MaintenanceService.Freins()
                )
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

