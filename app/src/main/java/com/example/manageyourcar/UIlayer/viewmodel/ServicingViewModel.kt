package com.example.manageyourcar.UIlayer.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.composeView.UIState.ServicingUIState
import com.example.manageyourcar.domainLayer.useCaseRoom.user.AddUserToRoomUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ServicingViewModel : ViewModel(), KoinComponent {
    private lateinit var navController : NavController
    private val _uiState = MutableStateFlow(arrayListOf(ServicingUIState()))
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            // Appel useCase pour récupérer tout les entretiens planifié
        }
    }

    fun setNavController(view : View) {
        navController= Navigation.findNavController(view)
    }
    fun onEvent(event: onServicingEvent) {
        when (event) {
            is onServicingEvent.onButtonAddServicingPush -> navController.navigate(R.id.action_viewServicingFragment_to_addMaintenanceFragment)
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
