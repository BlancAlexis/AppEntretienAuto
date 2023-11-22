package com.example.manageyourcar.UIlayer.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.composeView.UIState.MaintenanceListUiState
import com.example.manageyourcar.UIlayer.composeView.UIState.ServicingUIState
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.dataLayer.model.MaintenanceService
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.AddCarMaintenanceUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.GetAllUserMaintenanceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ListMaintenanceViewModel : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(MaintenanceListUiState())
    val uiState = _uiState.asStateFlow()

    private val getAllUserMaintenanceUseCase by inject<GetAllUserMaintenanceUseCase>()
    private val addCarMaintenanceUseCase by inject<AddCarMaintenanceUseCase>()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllUserMaintenanceUseCase.invoke().collect { result ->
                when (result) {
                    is Ressource.Error -> TODO()
                    is Ressource.Loading -> listLoad()
                    is Ressource.Success -> listLoading(result.data)
                }
            }
        }
    }

    private fun listLoading(newData: List<Entretien>?) {
        _uiState.update {
            it.copy(
                isLoading = false,
                listUiState = newData?.map { entretien ->
                    val carName = entretien.carID.toString() ?: ""
                    val mileage = entretien.mileage.toString()
                    val progressIndicator = 0.5f
                    val description = when(entretien.service) {
                        is MaintenanceService.Freins -> entretien.service.name
                        is MaintenanceService.Pneus -> entretien.service.name
                        is MaintenanceService.Vidange -> entretien.service.name
                    }
                    ServicingUIState(
                        carName = carName,
                        mileage = mileage,
                        progressIndicator = progressIndicator,
                        description = description
                    )
                } ?: emptyList()
            )
        }
    }

    private fun listLoad() {
        _uiState.update {
            it.copy(
isLoading = true
            )
        }
    }



        fun onEvent(event: onMaintenanceListEvent) {
            when (event) {
              is onMaintenanceListEvent.onButtonAddMaintenancePush -> {}

                is onMaintenanceListEvent.onSortMethodChanged -> changeSortMethod(event)
            }
        }
        private fun changeSortMethod(event: onMaintenanceListEvent.onSortMethodChanged) {
            //Trier le uiState
        }

        fun ServicingListToUiState() {
            /*_uiState.update {
                it.copy(

                )
            }
        }*/
        }
    }

    sealed interface onMaintenanceListEvent {
        object onButtonAddMaintenancePush : onMaintenanceListEvent
        class onSortMethodChanged(val newMethod: String) : onMaintenanceListEvent
    }
