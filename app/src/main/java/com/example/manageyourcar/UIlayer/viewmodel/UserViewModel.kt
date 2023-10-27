package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.dataApi.util.Ressource
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkImmatUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import com.example.manageyourcar.dataRoom.useCase.car.AddCarToRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.car.GetCarFromRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.user.AddUserToRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.user.GetUserFromRoomUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserViewModel : ViewModel(), KoinComponent {
    val isConnect = MutableLiveData<Boolean>()
    val liveDataIsCarAdd = MutableLiveData<Boolean>()
    val getVehiculeBySivNetworkUseCase by inject<GetVehiculeByNetworkUseCase>()
    val getVehiculeByImmatNetworkUseCase by inject<GetVehiculeByNetworkImmatUseCase>()
    val addCarToRoomUseCase by inject<AddCarToRoomUseCase>()
    val getCarToRoomUseCase by inject<GetCarFromRoomUseCase>()
    val addUserToRoomUseCase by inject<AddUserToRoomUseCase>()
    val getUserFromRoomUseCase by inject<GetUserFromRoomUseCase>()


    fun addNewUser(name: String, password: String, mail: String) {
        //Traitement de l'information puis envoie du résultat
        isConnect.postValue(true)
    }

    fun checkUserIdentifiant(login: String, password: String) {
        viewModelScope.launch {
            val roomUser = getUserFromRoomUseCase.getUserFromRoom()
            if (roomUser.get(0).password == password && roomUser.get(0).login == login) {
                isConnect.postValue(true)
            }
        }
    }

    fun getCarByImmat(immat: String) {
        viewModelScope.launch {
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
        viewModelScope.launch(Dispatchers.IO) {
            addCarToRoomUseCase.addCarToRoom(1, "d", "e")
            println(getCarToRoomUseCase.getCarFromRoom().toString() + "Jules le bozo")

        }
    }

    fun getCarToRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            println(getCarToRoomUseCase.getCarFromRoom()[0].toString() + "Jules le bozo")

        }
    }

    fun addUserToRoom(id: Int, name: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addUserToRoomUseCase.addUserToRoom(id, name, password)
            println("TEST JULES : " + getUserFromRoomUseCase.getUserFromRoom().toString())

        }
    }

    fun getUserToRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            println(getUserFromRoomUseCase.getUserFromRoom()[0].toString() + "Jules le bozo")

        }
    }


}
