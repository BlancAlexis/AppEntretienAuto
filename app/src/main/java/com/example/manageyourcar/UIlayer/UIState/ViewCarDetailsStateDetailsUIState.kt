package com.example.manageyourcar.UIlayer.UIState

import com.example.manageyourcar.dataLayer.model.CarLocal

sealed class ViewCarDetailsState {
    object Loading : ViewCarDetailsState()
    data class ViewCarDetailsStateDetailsUIState(
        val carLocals: List<CarLocal> = listOf()
    ) : ViewCarDetailsState()
}
