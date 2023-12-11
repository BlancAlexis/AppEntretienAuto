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
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.MaintenanceWithCarEntity
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.dataLayer.model.MaintenanceService
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.AddCarMaintenanceUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.GetAllUserMaintenanceUseCase
import com.example.manageyourcar.domainLayer.utils.MaintenanceActScheddule
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

    private lateinit var navController : NavController
    private val getAllUserMaintenanceUseCase by inject<GetAllUserMaintenanceUseCase>()
    private val addCarMaintenanceUseCase by inject<AddCarMaintenanceUseCase>()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllUserMaintenanceUseCase.invoke().collect { result ->
                when (result) {
                    is Ressource.Error -> TODO()
                    is Ressource.Loading -> listLoad()
                    is Ressource.Success -> result.data?.let { listLoading(it) }
                }
            }
        }
    }

    fun onInternetLost(bool : Boolean) {
        _uiState.update {
            it.copy(
                onInternetLost = bool
            )
        }
    }
    fun setNavController(view: View) {
        navController = Navigation.findNavController(view)
    }
    private fun listLoading(newData: List<MaintenanceWithCarEntity>) {
        _uiState.update {
            it.copy(
                isLoading = false,
                listUiState = newData.map { entretien ->
             var progressIndicator : Int = 0
                    var description : String = ""
             when(entretien.maintenanceEntity.serviceType) {
                 is MaintenanceService.Freins -> {
                     description=entretien.maintenanceEntity.serviceType.name
                     progressIndicator =  MaintenanceActScheddule.valueOf(entretien.maintenanceEntity.serviceType.name.uppercase()).km-entretien.maintenanceEntity.mileage
                 }
                 is MaintenanceService.Pneus -> {
                     description=entretien.maintenanceEntity.serviceType.name
                     progressIndicator =  entretien.maintenanceEntity.mileage/MaintenanceActScheddule.valueOf(entretien.maintenanceEntity.serviceType.name.uppercase()).km
                 }
                 is MaintenanceService.Vidange -> entretien.maintenanceEntity.serviceType.name
             }
             ServicingUIState(
                 carName = entretien.carEntity.model,
             mileage = entretien.maintenanceEntity.mileage.toString(),
             progressIndicator = progressIndicator.toFloat(),
             description = description,
             ) })
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
              is onMaintenanceListEvent.onButtonAddMaintenancePush -> {  navController?.navigate(R.id.addMaintenanceFragment) }
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
