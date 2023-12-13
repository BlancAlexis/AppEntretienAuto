package com.example.manageyourcar.UIlayer.composeView.UIState

import com.example.manageyourcar.dataLayer.model.Car
import java.util.Date

data class ViewCarDetailsUIState(
    val cars : List<Car> = listOf()
) {
}