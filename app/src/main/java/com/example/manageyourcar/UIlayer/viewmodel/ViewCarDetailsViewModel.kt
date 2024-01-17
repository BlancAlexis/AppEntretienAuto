package com.example.manageyourcar.UIlayer.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.composeView.UIState.ViewCarDetailsState
import com.example.manageyourcar.UIlayer.view.fragments.ViewCarDetailsFragmentDirections
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.domainLayer.useCaseRoom.car.DeleteCarRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ViewCarDetailsViewModel : ViewModel(), KoinComponent {
    private val getUserCarsUseCase by inject<GetUserCarsUseCase>()
    private val deleteCarRoomUseCase by inject<DeleteCarRoomUseCase>()
    private val _uiState = MutableStateFlow<ViewCarDetailsState>(ViewCarDetailsState.Loading)
    val uiState = _uiState.asStateFlow()
    private lateinit var navController: NavController

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserCarsUseCase.invoke().collect { result ->
                when (result) {
                    is Ressource.Error -> TODO()
                    is Ressource.Loading -> TODO()
                    is Ressource.Success -> result.data?.let {
                        updateListCar(it)
                    }
                }
            }
        }
    }

    fun onEvent(event: ViewCarDetailsEvent) {
        when (event) {
            is ViewCarDetailsEvent.OnClickAddCarButton -> {
                navController.navigate(R.id.action_viewCarDetailsFragment_to_AddCarFragment)
            }

            is ViewCarDetailsEvent.OnUpdateMileage -> {
                val action =
                    ViewCarDetailsFragmentDirections.actionViewCarDetailsFragmentToUpdateCarMileage(
                        myArg = _uiState.value.let {
                            it.let {
                                it as ViewCarDetailsState.ViewCarDetailsStateDetailsUIState
                            }.cars[0] // A changer
                        })
                navController.navigate(action)
            }

            is ViewCarDetailsEvent.OnDeleteCar ->
                viewModelScope.launch(Dispatchers.IO) {
                    deleteCarRoomUseCase.deleteCar(event.idCar)
                }
        }


    }

    fun setNavController(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun updateListCar(data: List<Car>) {
        _uiState.update {
            ViewCarDetailsState.ViewCarDetailsStateDetailsUIState(
                cars = data
            )
        }
    }

}

sealed interface ViewCarDetailsEvent {
    object OnClickAddCarButton : ViewCarDetailsEvent
    object OnUpdateMileage : ViewCarDetailsEvent
    data class OnDeleteCar(val idCar: Car) : ViewCarDetailsEvent
}