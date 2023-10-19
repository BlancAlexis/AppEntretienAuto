package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.dataApi.util.Ressource
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkImmatUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.AddCarToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.GetCarFromRoomUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserViewModel : ViewModel(), KoinComponent {
    val liveDataConnect = MutableLiveData<Boolean>()
    val liveDataIsCarAdd = MutableLiveData<Boolean>()
    val getVehiculeBySivNetworkUseCase by inject<GetVehiculeByNetworkUseCase>()
    val getVehiculeByImmatNetworkUseCase by inject<GetVehiculeByNetworkImmatUseCase>()
    val addCarToRoomUseCase by inject<AddCarToRoomUseCase>()
    val getCarToRoomUseCase by inject<GetCarFromRoomUseCase>()


    fun addNewUser(name: String, password: String, mail: String) {
        //Traitement de l'information puis envoie du résultat
        liveDataConnect.postValue(true)
    }

    fun getCarByImmat(immat: String) {
        viewModelScope.launch {
            getVehiculeByImmatNetworkUseCase.getVehiculeByImmat(immat).collect{ result ->
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
                        // Requete pour vérif si voiture existe puis enregistrement room

                    }
                }
            }
        }
    }

    fun getCarBySIV(SIV: String) {
        viewModelScope.launch {
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
                        // Requete pour vérif si voiture existe puis enregistrement room

                    }
                }
            }
        }
    }

        fun addCarToRoom() {
//            viewModelScope.launch {
//                addCarToRoomUseCase.addCarToRoom(1, "d", "e")
//                println(getCarToRoomUseCase.getCarFromRoom().toString() + "Jules le bozo");
//
//            }
        }
}

