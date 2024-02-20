package com.example.manageyourcar.UIlayer.UIState

data class MaintenanceListUiState(
    val listUiState: List<ServicingUIState> = listOf(),
    val isLoading: Boolean = true,
    )