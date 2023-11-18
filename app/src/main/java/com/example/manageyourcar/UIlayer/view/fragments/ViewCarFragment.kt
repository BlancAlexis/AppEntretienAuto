package com.example.manageyourcar.UIlayer.view.fragments

import com.example.manageyourcar.domainLayer.useCaseRoom.car.AddCarToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class ViewCarFragment : KoinComponent                                       {
    private val addCarToRoomUseCase by inject<AddCarToRoomUseCase>()
    private val getCarsFromRoomUseCase by inject<GetUserCarsUseCase>()
}