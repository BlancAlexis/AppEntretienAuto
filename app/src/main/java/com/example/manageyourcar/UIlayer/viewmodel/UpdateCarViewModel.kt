package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.composeView.UIState.SignInUiState
import com.example.manageyourcar.UIlayer.composeView.UIState.UpdateMileage
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateCarViewModel : ViewModel(), KoinComponent {

    private val getUserCarsUseCase by inject<GetUserCarsUseCase>()
    private val _uiState = MutableStateFlow(UpdateMileage())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserCarsUseCase.invoke().collect { result ->
                when (result) {
                    is Ressource.Error -> TODO()
                    is Ressource.Loading -> TODO()
                    is Ressource.Success -> result.data?.let { data ->
                            _uiState.update {
                                it.copy(
                                    ListCar = data
                                )
                            }
                    }
                }
            }
        }
    }

    fun onEvent(event: UpdateCatEvent) {
        when (event) {
            UpdateCatEvent.OnUpdateMileage -> TODO()
        }
    }
}
sealed interface UpdateCatEvent {
    object OnUpdateMileage :UpdateCatEvent
}