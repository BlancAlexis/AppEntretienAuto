package com.example.manageyourcar.UIlayer.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.UIlayer.UIState.AddCarUIState
import com.example.manageyourcar.UIlayer.UIUtil
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.domainLayer.mappers.CarRetrofitToCar.toCarGlobal
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkImmatUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.AddCarRoomUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddCarViewModel : ViewModel(), KoinComponent {
    private val addCarRoomUseCase by inject<AddCarRoomUseCase>()
    private val getVehiculeBySivNetworkUseCase by inject<GetVehiculeByNetworkUseCase>()
    private val getVehiculeByImmatNetworkUseCase by inject<GetVehiculeByNetworkImmatUseCase>()
    private val uIUtil by inject<UIUtil>()
    val dismissFragment: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _uiState = MutableStateFlow(AddCarUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: OnCarRequest) {
        when (event) {
            is OnCarRequest.OnClickSearchCarWithImmatButton -> searchCarWithImmat()
            is OnCarRequest.OnImmatChanged -> onChangedImmat(event)
            is OnCarRequest.OnVINChanged -> onChangedVIN(event)
            is OnCarRequest.OnDismissAddCarFragment -> dismissFragment.postValue(true)
            OnCarRequest.OnClickAddCarButton -> addCarToRoom()
            OnCarRequest.OnClickSearchCarWithSIVButton -> searchCarWithSIV()
        }
    }

    private fun searchCarWithSIV() {
        uiState.value.inputVIN?.let {
            if (it.length == 17) {
                getCarBySIV(it)
            } else {
                uIUtil.displayToast("Veuillez entre une plaque valide")
            }
        } ?: run {
            uIUtil.displayToast("Error vin")
        }
    }

    private fun searchCarWithImmat() {
        uiState.value.inputImmat?.let { immat ->
            if (immat.matches(Regex("^([A-Z]{2})-([0-9]{3})-([A-Z]{2})$"))) {
                getCarByImmat(immat)
            } else {

                Toast.makeText(AppApplication.instance.applicationContext, "champs vide", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(AppApplication.instance.applicationContext, "error immat", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onChangedImmat(event: OnCarRequest.OnImmatChanged) {
        _uiState.update {
            it.copy(
                inputImmat = event.newValue
            )
        }
    }

    private fun onChangedVIN(event: OnCarRequest.OnVINChanged) {
        _uiState.update {
            it.copy(
                inputVIN = event.newValue
            )
        }
    }

    fun getCarBySIV(siv: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getVehiculeBySivNetworkUseCase.getVehiculeBySiv(siv).collect { result ->
                when (result) {
                    is Ressource.Error -> {
                        uIUtil.displayToastSuspend("Erreur lors de la requête ${result.message}")
                    }
                    is Ressource.Success -> {
                        result.data?.let { setCar(it.toCarGlobal()) }
                    }
                    else -> {}
                }
            }
        }
    }

    private fun setCar(carLocal: CarLocal) {
        _uiState.update {
            it.copy(
                carLocalFind = carLocal
            )
        }
    }

    private fun getCarByImmat(immat: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getVehiculeByImmatNetworkUseCase.getVehiculeByImmat(immat).collect { result ->
                when (result) {
                    is Ressource.Error -> {
                        uIUtil.displayToastSuspend("Erreur lors de la requête ${result.message}")
                    }
                    is Ressource.Success -> {
                        result.data?.let { setCar(it) }
                    }
                    else -> {}
                }
            }
        }
    }

    private fun addCarToRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            when (addCarRoomUseCase.addCarToRoom(uiState.value.carLocalFind!!)) {
                is Ressource.Success -> {
                        uIUtil.displayToastSuspend("Voiture ajoutée avec succès")
                        dismissFragment.postValue(true)
                    }
                is Ressource.Error -> {
                    uIUtil.displayToastSuspend("Problème lors de l'ajout de la voiture")
                    dismissFragment.postValue(true)
                }
                else -> {}
            }
        }
    }

    fun onInternetLost(bool: Boolean) {
        _uiState.update {
            it.copy(
                onInternetLost = bool
            )
        }
    }


}

sealed interface OnCarRequest {
    object OnClickSearchCarWithImmatButton : OnCarRequest
    object OnClickSearchCarWithSIVButton : OnCarRequest
    object OnClickAddCarButton : OnCarRequest
    object OnDismissAddCarFragment : OnCarRequest
    data class OnVINChanged(val newValue: String) : OnCarRequest
    data class OnImmatChanged(val newValue: String) : OnCarRequest
}


