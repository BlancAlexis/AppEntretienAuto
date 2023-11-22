package com.example.manageyourcar.UIlayer.composeView.UIState

data class MaintenanceListUiState (
    val listUiState: List<ServicingUIState> = listOf(),
    val isLoading : Boolean=true
)