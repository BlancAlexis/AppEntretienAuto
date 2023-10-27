package com.example.manageyourcar.UIlayer.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.manageyourcar.composeView.UIState.AddCarUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddCarViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddCarUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event : onCarRequest) {
        when (event) {
            is onCarRequest.onClickButton ->  Log.i("","")
            is onCarRequest.onImmatChanged -> Log.i("","")
            is onCarRequest.onVINChanged ->  Log.i("","")
        }
    }

}

sealed interface onCarRequest{
    object onClickButton : onCarRequest
    data class onVINChanged( val newValue: String) : onCarRequest
    data class onImmatChanged( val newValue: String) : onCarRequest
}


