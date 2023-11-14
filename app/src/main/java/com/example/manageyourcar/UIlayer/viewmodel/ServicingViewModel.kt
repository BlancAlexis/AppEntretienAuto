package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.UIlayer.composeView.UIState.ServicingUIState
import com.example.manageyourcar.domainLayer.useCaseRoom.user.AddUserToRoomUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ServicingViewModel : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(arrayListOf(ServicingUIState()))
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            // Appel useCase pour récupérer tout les entretiens planifié
        }
    }

    fun onEvent(event: onServicingEvent) {
        when (event) {
            is onServicingEvent.onButtonAddServicingPush -> TODO()
            is onServicingEvent.onSortMethodChanged -> changeSortMethod(event)
        }
    }

    private fun changeSortMethod(event: onServicingEvent.onSortMethodChanged) {
        //Trier le uiState
    }

    fun ServicingListToUiState() {
        /*_uiState.update {
            it.copy(

            )
        }
    }*/
    }
}
sealed interface onServicingEvent{
    object onButtonAddServicingPush : onServicingEvent
    class onSortMethodChanged( val newMethod : String) : onServicingEvent
}