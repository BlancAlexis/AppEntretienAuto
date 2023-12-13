package com.example.manageyourcar.UIlayer.view.fragments

import com.example.manageyourcar.domainLayer.useCaseRoom.car.AddCarRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ViewCarFragment : KoinComponent                                       {
    private val addCarRoomUseCase by inject<AddCarRoomUseCase>()
    private val getCarsFromRoomUseCase by inject<GetUserCarsUseCase>()
}