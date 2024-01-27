package com.example.manageyourcar.UIlayer.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.UIState.ViewCarDetailsState
import com.example.manageyourcar.UIlayer.view.fragments.ViewCarDetails.ViewCarDetailsFragmentDirections
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.Car
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
    private val _uiState = MutableStateFlow<ViewCarDetailsState>(ViewCarDetailsState.Loading)
    val uiState = _uiState.asStateFlow()
    private lateinit var navController: NavController

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserCarsUseCase.invoke().collect { result ->
                when (result) {
                    is Ressource.Error -> Log.e("ViewCarDetailsViewModel", "Utilisateur inconnu")
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
                val action = ViewCarDetailsFragmentDirections.actionViewCarDetailsFragmentToUpdateCarMileage(
                        myArg = _uiState.value.let {
                            it.let {
                                it as ViewCarDetailsState.ViewCarDetailsStateDetailsUIState
                            }.cars[event.position]
                        })
                navController.navigate(action)
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
    data class OnUpdateMileage(val position : Int) : ViewCarDetailsEvent
}