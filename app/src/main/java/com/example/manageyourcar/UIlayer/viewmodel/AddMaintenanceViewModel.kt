package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.UIlayer.UIState.AddVehiculeMaintenanceUiState
import com.example.manageyourcar.UIlayer.UIUtil
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.dataLayer.model.MaintenanceServiceType
import com.example.manageyourcar.domainLayer.mappers.MapperMaintenanceView.toMaintenanceService
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.AddCarMaintenanceUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Date

class AddMaintenanceViewModel constructor(
    private val cacheManagerRepository: CacheManagerRepository,
    private val uiUtil: UIUtil
) : ViewModel(), KoinComponent {
    private val getUserCarsUseCase by inject<GetUserCarsUseCase>()
    val isMaintenanceAdd = MutableLiveData<Boolean>(false)

    private val _uiState = MutableStateFlow(AddVehiculeMaintenanceUiState())
    val uiState = _uiState.asStateFlow()

    private val addCarMaintenanceUseCase by inject<AddCarMaintenanceUseCase>()

    private lateinit var selectedCarLocal: CarLocal
    private lateinit var selectedMaintenance: MaintenanceServiceType
    //TODO: refactor

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    init {
        getCachedUserCars()
    }

    private fun getCachedUserCars() {
        viewModelScope.launch(ioDispatcher) {
            when (val result = cacheManagerRepository.getUserCarList()) {
                is Ressource.Error -> {
                    getUserCarsLocalStorage()
                }

                is Ressource.Success -> result.data?.let {
                    checkCars(it)
                }

                else -> {}
            }
        }
    }

    private fun addMaintenanceLocalStorage() {
        viewModelScope.launch(ioDispatcher) {
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
                is Ressource.Error -> uiUtil.displayToastSuspend("échec de l'ajout")
                is Ressource.Success -> {
                    isMaintenanceAdd.postValue(true)
                }

                else -> {}
            }
        }
    }

    private fun checkCars(cars: List<CarLocal>) {
        if (cars.isEmpty()) {
            isMaintenanceAdd.postValue(true)
        }
        updateListCar(cars)
        if (cars.isNotEmpty()) {
            selectedCarLocal = cars[0]
        }
        _uiState.update {
            it.copy(
                listMaintenance = MaintenanceServiceType.values().toList()
            )
        }
        selectedMaintenance = MaintenanceServiceType.values()[0]
    }

    private fun getUserCarsLocalStorage() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserCarsUseCase.invoke().collect { result ->
                when (result) {
                    is Ressource.Error -> uiUtil.displayToastSuspend(
                        result.error?.localizedMessage ?: "erreur"
                    )

                    is Ressource.Success -> checkCars(result.data ?: emptyList())
                    else -> {}
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
            is OnMaintenanceEvent.OnCarSelectedChanged -> onCarChanged(event.newCarSelected)
            is OnMaintenanceEvent.OnMaintenanceSelectedChanged -> onMaintenanceChanged(event.newMaintenanceServiceType)
            is OnMaintenanceEvent.OnMileageChanged -> onMileageChanged(event.newMileage)
            is OnMaintenanceEvent.OnDateChanged -> onDateChanged(Date(event.newDate))
            OnMaintenanceEvent.OnClickAddMaintenanceButton -> {
                addMaintenanceLocalStorage()
            }

            is OnMaintenanceEvent.OnPriceChanged -> onPriceChanged(event.newPrice)
        }

    }

    private fun onCarChanged(newValue: CarLocal) {
        selectedCarLocal = newValue
    }

    private fun onPriceChanged(newValue: Int) {
        _uiState.update {
            it.copy(
                price = newValue.toString()
            )
        }
    }


    private fun onMaintenanceChanged(newValue: MaintenanceServiceType) {
        selectedMaintenance = newValue
    }

    private fun onMileageChanged(newValue: Int) {
        _uiState.update {
            it.copy(
                mileage = newValue.toString()
            )
        }
    }

    private fun onDateChanged(newDate: Date) {
        _uiState.update {
            it.copy(
                date = newDate
            )
        }
    }
}

sealed interface OnMaintenanceEvent {
    object OnClickAddMaintenanceButton : OnMaintenanceEvent
    data class OnDateChanged(val newDate: String) : OnMaintenanceEvent
    data class OnMileageChanged(val newMileage: Int) : OnMaintenanceEvent
    data class OnPriceChanged(val newPrice: Int) : OnMaintenanceEvent
    data class OnMaintenanceSelectedChanged(val newMaintenanceServiceType: MaintenanceServiceType) :
        OnMaintenanceEvent

    data class OnCarSelectedChanged(val newCarSelected: CarLocal) : OnMaintenanceEvent

}

