package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.dataApi.util.Ressource
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.AddCarToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.GetCarFromRoomUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserViewModel : ViewModel(), KoinComponent {
    val liveDataConnect = MutableLiveData<Boolean>()
    val liveDataIsCarAdd = MutableLiveData<Boolean>()
    val getVehiculeByNetworkUseCase by inject<GetVehiculeByNetworkUseCase>()
    val addCarToRoomUseCase by inject<AddCarToRoomUseCase>()
    val getCarToRoomUseCase by inject<GetCarFromRoomUseCase>()


    fun addNewUser(name: String, password: String, mail: String) {
        //Traitement de l'information puis envoie du résultat
        liveDataConnect.postValue(true)
    }

    fun addNewCarBySIV(SIV: String) {
        viewModelScope.launch {
            getVehiculeByNetworkUseCase.getVehiculeBySiv(SIV).collect { result ->
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

//    fun addNewCarByImmat(immat: String) {
//        viewModelScope.launch {
//            getVehiculeByImmatUseCase.getVehiculeByImmat("CP-370-YK").collect { result ->
//                when (result) {
//                    is Ressource.Loading -> {
//                        println("load")
//                    }
//
//                    is Ressource.Error -> {
//                        println("Ressource.Error" + result.message)
//                        // Faire une classe gestion erreur
//                    }
//
//                    is Ressource.Success -> {
//                        println("Ressource.Success" + result.data)
//                        // Requete pour vérif si voiture existe puis enregistrement room
//
//                    }
//                }
//            }
//            // Requete pour vérif si voiture existe puis enregistrement room
//
//        }

        fun addCarToRoom() {
           viewModelScope.launch {
               addCarToRoomUseCase.addCarToRoom(1, "d", "e")
               println(getCarToRoomUseCase.getCarFromRoom().toString() + "Jules le bozo");

            }
        }
}

