package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.UIlayer.UIState.AddCarUIState
import com.example.manageyourcar.UIlayer.viewEvent.UIUtil
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import com.example.manageyourcar.domainLayer.mappers.CarRetrofitToCar.toCarGlobal
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkImmatUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.AddCarRoomUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddCarViewModel constructor(private val cacheManager: CacheManagerRepository) : ViewModel(),
    KoinComponent {
    private val addCarRoomUseCase by inject<AddCarRoomUseCase>()
    private val getVehiculeBySivNetworkUseCase by inject<GetVehiculeByNetworkUseCase>()
    private val getVehiculeByImmatNetworkUseCase by inject<GetVehiculeByNetworkImmatUseCase>()
    private val uIUtil by inject<UIUtil>()
    val dismissFragment: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _uiState = MutableStateFlow(AddCarUIState())
    val uiState = _uiState.asStateFlow()

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    fun onEvent(event: OnCarRequest) {
        when (event) {
            is OnCarRequest.OnClickSearchCarByRegistrationNumberButton -> searchCarByRegistrationNumber()
            is OnCarRequest.OnRegistrationNumberChanged -> onChangedRegistrationNumber(event)
            is OnCarRequest.OnVINNumberChanged -> onChangedVINNumber(event)
            is OnCarRequest.OnDismissAddCarFragment -> dismissFragment.postValue(true)
            OnCarRequest.OnClickAddCarButton -> addCarLocalStorage()
            OnCarRequest.OnClickSearchCarWithSIVButton -> checkCarSIVNumber()
        }
    }

    private fun checkCarSIVNumber() {
        uiState.value.inputVIN?.let { vinNumber ->
            if (vinNumber.length == 17) {
                getCarBySIVNumber(vinNumber)
            } else {
                uIUtil.displayToast("Veuillez entre un VIN valide")
            }
        } ?: run {
            uIUtil.displayToast("Error vin")
        }
    }

    private fun searchCarByRegistrationNumber() {
        uiState.value.inputImmat?.let { registrationNumber ->
            if (registrationNumber.matches(Regex("^([A-Z]{2})-([0-9]{3})-([A-Z]{2})$"))) {
                getCarByRegistrationNumber(registrationNumber)
            } else {
                uIUtil.displayToast("error immat")
            }
        }
    }

    private fun onChangedRegistrationNumber(event: OnCarRequest.OnRegistrationNumberChanged) {
        _uiState.update {
            it.copy(
                inputImmat = event.newRegistrationNumber
            )
        }
    }

    private fun onChangedVINNumber(event: OnCarRequest.OnVINNumberChanged) {
        _uiState.update {
            it.copy(
                inputVIN = event.newVINNumber
            )
        }
    }

    private fun getCarBySIVNumber(sivNumber: String) {
        viewModelScope.launch(ioDispatcher) {
            getVehiculeBySivNetworkUseCase.getVehiculeBySiv(sivNumber).collect { result ->
                when (result) {
                    is Ressource.Error -> {
                        uIUtil.displayToastSuspend("Erreur lors de la requête ${result.message}")
                    }

                    is Ressource.Success -> {
                        if (result.data?.model == null ){
                            uIUtil.displayToastSuspend("Voiture introuvable")

                        }
                        else {
                            result.data.let { setCar(it.toCarGlobal(cacheManager.getUserId().data!!)) }
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    private fun setCar(car: Car) {
        _uiState.update {
            it.copy(
                carFind = car
            )
        }
    }

    private fun getCarByRegistrationNumber(registrationNumber: String) {
        viewModelScope.launch(ioDispatcher) {
            getVehiculeByImmatNetworkUseCase.getVehiculeByImmat(registrationNumber)
                .collect { result ->
                    when (result) {
                        is Ressource.Error -> {
                            uIUtil.displayToastSuspend("Erreur lors de la requête ${result.message}")
                        }

                        is Ressource.Success -> {
                            result.data?.let {
                                if (it.model == null ){
                                    uIUtil.displayToastSuspend("Voiture introuvable")

                                }
                                else {
                                    setCar(it)
                                }
                            }
                        }

                        else -> {}
                    }
                }
        }
        //TODO: pas besoin de flow dans ce cas?
    }

    private fun addCarLocalStorage() {
        viewModelScope.launch(ioDispatcher) {
            uiState.value.carFind?.let { car ->
                when (addCarRoomUseCase.addCarToRoom(car)) {
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
    }
}

sealed interface OnCarRequest {
    object OnClickSearchCarByRegistrationNumberButton : OnCarRequest
    object OnClickSearchCarWithSIVButton : OnCarRequest
    object OnClickAddCarButton : OnCarRequest
    object OnDismissAddCarFragment : OnCarRequest
    data class OnVINNumberChanged(val newVINNumber: String) : OnCarRequest
    data class OnRegistrationNumberChanged(val newRegistrationNumber: String) : OnCarRequest
}


