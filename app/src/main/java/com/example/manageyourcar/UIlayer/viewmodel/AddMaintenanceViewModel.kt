package com.example.manageyourcar.UIlayer.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.UIlayer.UIState.AddVehiculeMaintenanceUiState
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.dataLayer.model.MaintenanceServiceType
import com.example.manageyourcar.domainLayer.mappers.MapperMaintenanceView.toMaintenanceService
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
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

class AddMaintenanceViewModel constructor(private val cacheManagerRepository: CacheManagerRepository): ViewModel(), KoinComponent {
    private val getUserCarsUseCase by inject<GetUserCarsUseCase>()
    val isMaintenanceAdd = MutableLiveData<Boolean>(false)

    private val _uiState = MutableStateFlow(AddVehiculeMaintenanceUiState())
    val uiState = _uiState.asStateFlow()

    private val addCarMaintenanceUseCase by inject<AddCarMaintenanceUseCase>()

    private lateinit var selectedCarLocal: CarLocal
    private lateinit var selectedMaintenance: MaintenanceServiceType


    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = cacheManagerRepository.getUserCarList()) {
                is Ressource.Error -> {
                    getUserCarsUseCase.invoke().collect { result ->
                        when (result) {
                            is Ressource.Error -> Toast.makeText(
                                AppApplication.instance.applicationContext, "Erreur lors du chargement des voitures", Toast.LENGTH_SHORT).show()
                            is Ressource.Success -> result.data?.let {
                                if (it.isEmpty()) {
                                    isMaintenanceAdd.postValue(true)
                                }
                                updateListCar(it)
                                if (it.isNotEmpty()) {
                                    selectedCarLocal = it[0]
                                }
                                _uiState.update {
                                    it.copy(
                                        listMaintenance = MaintenanceServiceType.values().toList()
                                    )
                                }
                                selectedMaintenance = MaintenanceServiceType.values()[0]
                            }
                            else -> {}
                        }
                    }
                }
                is Ressource.Success -> result.data?.let {
                    if (it.isEmpty()) {
                        isMaintenanceAdd.postValue(true)
                    }
                    updateListCar(it)
                    if (it.isNotEmpty()) {
                        selectedCarLocal = it[0]
                    }
                    _uiState.update {
                        it.copy(
                            listMaintenance = MaintenanceServiceType.values().toList()
                        )
                    }
                    selectedMaintenance = MaintenanceServiceType.values()[0]
                }
                else -> {}
            }
        }
    }

    private fun addMaintenanceAct() {
        viewModelScope.launch(Dispatchers.IO) {
            when (addCarMaintenanceUseCase.addMaintenanceOperation(
                Entretien(
                    userID = null,
                    carID = selectedCarLocal.carID,
                    mileage = uiState.value.mileage.toInt(),
                    price = uiState.value.price.toInt(),
                    date = uiState.value.date ?: Date(),
                    service = selectedMaintenance.toMaintenanceService()
                )
            )) {
                is Ressource.Error -> TODO()
                is Ressource.Loading -> TODO()
                is Ressource.Success -> {
                    isMaintenanceAdd.postValue(true)
                }
            }
        }
    }

    private fun updateListCar(data: List<CarLocal>) {
        _uiState.update {
            it.copy(
                listCarLocals = data
            )
        }
    }

    fun onEvent(event: OnMaintenanceEvent) {
        when (event) {
            is OnMaintenanceEvent.OnCarSelectedChanged -> OnCarChanged(event.newValue)
            is OnMaintenanceEvent.OnMaintenanceSelectedChanged -> OnMaintenanceChanged(event.newValue)
            is OnMaintenanceEvent.OnMileageChanged -> OnMileageChanged(event.newValue)
            is OnMaintenanceEvent.OnDateChanged -> OnDateChanged(Date(event.newDate))
            OnMaintenanceEvent.OnClickAddMaintenanceButton -> {
                addMaintenanceAct()
            }

            is OnMaintenanceEvent.OnPriceChanged -> OnPriceChanged(event.newValue)
        }

    }

    private fun OnCarChanged(newValue: CarLocal) {
        selectedCarLocal = newValue
    }

    private fun OnPriceChanged(newValue: Int) {
        _uiState.update {
            it.copy(
                price = newValue.toString()
            )
        }
    }


    private fun OnMaintenanceChanged(newValue: MaintenanceServiceType) {
        selectedMaintenance = newValue
    }

    private fun OnMileageChanged(newValue: Int) {
        _uiState.update {
            it.copy(
                mileage = newValue.toString()
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


    fun onInternetLost(bool: Boolean) {
        _uiState.update {
            it.copy(
                onInternetLost = bool
            )
        }
    }

}

sealed interface OnMaintenanceEvent {
    object OnClickAddMaintenanceButton : OnMaintenanceEvent
    data class OnDateChanged(val newDate: String) : OnMaintenanceEvent
    data class OnMileageChanged(val newValue: Int) : OnMaintenanceEvent
    data class OnPriceChanged(val newValue: Int) : OnMaintenanceEvent
    data class OnMaintenanceSelectedChanged(val newValue: MaintenanceServiceType) :
        OnMaintenanceEvent

    data class OnCarSelectedChanged(val newValue: CarLocal) : OnMaintenanceEvent

}

