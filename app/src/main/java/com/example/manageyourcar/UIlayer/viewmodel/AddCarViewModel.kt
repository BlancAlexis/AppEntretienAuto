package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.UIlayer.composeView.UIState.AddCarUIState
import com.example.manageyourcar.UIlayer.view.activities.OnApplicationEvent
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.model.Car
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkImmatUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.AddCarToRoomUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddCarViewModel : ViewModel(), KoinComponent {
    private val addCarToRoomUseCase by inject<AddCarToRoomUseCase>()
    private val getVehiculeBySivNetworkUseCase by inject<GetVehiculeByNetworkUseCase>()
    private val getVehiculeByImmatNetworkUseCase by inject<GetVehiculeByNetworkImmatUseCase>()


    private val _uiState = MutableStateFlow(AddCarUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: onCarRequest) {
            when (event) {
            is onCarRequest.onClickButton -> onValidation()
            is onCarRequest.onImmatChanged -> onChangedImmat(event)
            is onCarRequest.onVINChanged -> onChangedVIN(event)
        }
    }

    private fun onValidation() {
        if (uiState.value.inputImmat?.matches(Regex("^([A-Z]{2})-([0-9]{3})-([A-Z]{2})$")) == true) {
            getCarByImmat(uiState.value.inputImmat!!)
        } else if (uiState.value.inputVIN?.length == 17) {
            getCarBySIV(uiState.value.inputVIN!!)
        } else {
            // Aucun des deux OK, affichage pop-up?
        }
    }

    private fun onChangedImmat(event: onCarRequest.onImmatChanged) {
        _uiState.update {
            it.copy(
                inputImmat = event.newValue
            )
        }
    }

    private fun onChangedVIN(event: onCarRequest.onVINChanged) {
        _uiState.update {
            it.copy(
                inputVIN = event.newValue
            )
        }
    }

    fun getCarBySIV(SIV: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getVehiculeBySivNetworkUseCase.getVehiculeBySiv(SIV).collect { result ->
                when (result) {
                    is Ressource.Loading -> {
                        println("load")
                    }

                    is Ressource.Error -> {
                        println("Ressource.Error" + result.message)
                        // Faire une classe gestion erreur
                    }

                    is Ressource.Success -> {
                        println("Ressource.Success" + result.data)
                        checkIfUserRightCar(result.data)
                        // Requete pour vérif si voiture existe puis enregistrement room

                    }
                }
            }
        }
    }

    fun getCarByImmat(immat: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getVehiculeByImmatNetworkUseCase.getVehiculeByImmat(immat).collect { result ->
                when (result) {
                    is Ressource.Loading -> {
                        println("load")
                    }

                    is Ressource.Error -> {
                        println("Ressource.Error" + result.message)
                        // Faire une classe gestion erreur
                    }

                    is Ressource.Success -> {
                        println("Ressource.Success" + result.data)
                        checkIfUserRightCar(result.data)
                        // Requete pour vérif si voiture existe puis enregistrement room

                    }
                }
            }
        }
    }

    private fun checkIfUserRightCar(data: Car?) {
        //Display BottomSheetFragDialog
        addCarToRoom()
    }

    private fun addCarToRoom() {
        viewModelScope.launch {
            //addCarToRoomUseCase.addCarToRoom()
        }
    }


}

sealed interface onCarRequest {
    object onClickButton : onCarRequest
    data class onVINChanged(val newValue: String) : onCarRequest
    data class onImmatChanged(val newValue: String) : onCarRequest
}


