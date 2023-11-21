package com.example.manageyourcar.UIlayer.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.manageyourcar.UIlayer.composeView.UIState.AddVehiculeMaintenanceUiState
import com.example.manageyourcar.UIlayer.view.fragments.onMaintenanceEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class AddMaintenanceViewModel : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(AddVehiculeMaintenanceUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: onMaintenanceEvent) {
        when (event) {
            is onMaintenanceEvent.onCarChanged -> TODO()
            is onMaintenanceEvent.onMaintenanceChanged -> TODO()
            is onMaintenanceEvent.onMileageChanged -> TODO()
            is onMaintenanceEvent.onDateChanged -> Log.i("Date", "Dare ${event.newDate}")
            is onMaintenanceEvent.onValidatePressed -> TODO()
        }

    }

}