package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.UIlayer.composeView.UIState.AddVehiculeMaintenanceUiState
import com.example.manageyourcar.UIlayer.composeView.UIState.ViewCarDetailsUIState
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ViewCarDetailsViewModel: ViewModel(), KoinComponent {
    private val getUserCarsUseCase by inject<GetUserCarsUseCase>()
    private val _uiState = MutableStateFlow(ViewCarDetailsUIState())
    val uiState = _uiState.asStateFlow()

}