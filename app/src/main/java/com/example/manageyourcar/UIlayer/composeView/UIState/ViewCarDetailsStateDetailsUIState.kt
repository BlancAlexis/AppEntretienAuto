package com.example.manageyourcar.UIlayer.composeView.UIState

import com.example.manageyourcar.dataLayer.model.Car

sealed class ViewCarDetailsState {
    object Loading : ViewCarDetailsState()
    data class ViewCarDetailsStateDetailsUIState(
        val cars : List<Car> = listOf()
    )  : ViewCarDetailsState()
}
