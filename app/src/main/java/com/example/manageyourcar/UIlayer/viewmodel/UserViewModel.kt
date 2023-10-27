package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.dataApi.util.Ressource
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkImmatUseCase
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import com.example.manageyourcar.dataRoom.useCase.car.AddCarToRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.car.GetCarsFromRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.user.AddUserToRoomUseCase
import com.example.manageyourcar.dataRoom.useCase.user.GetUsersFromRoomUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.example.manageyourcar.dataRoom.model.Car

class UserViewModel : ViewModel(), KoinComponent {
    val liveDataConnect = MutableLiveData<Boolean>()
    val liveDataIsCarAdd = MutableLiveData<Boolean>()
    val liveDataCar = MutableLiveData<List<Car>>()
    val getVehiculeBySivNetworkUseCase by inject<GetVehiculeByNetworkUseCase>()
    val getVehiculeByImmatNetworkUseCase by inject<GetVehiculeByNetworkImmatUseCase>()
    val addCarToRoomUseCase by inject<AddCarToRoomUseCase>()
    val getCarToRoomUseCase by inject<GetCarsFromRoomUseCase>()
    val addUserToRoomUseCase by inject<AddUserToRoomUseCase>()
    val getUserFromRoomUseCase by inject<GetUsersFromRoomUseCase>()


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
           viewModelScope.launch(Dispatchers.IO) {
               addCarToRoomUseCase.addCarToRoom(1,
                   "d",
                   "e",
                   1,
                   "",
                   "",
                   "",
                   1,
                   1,
                   1,
                   1)
               println(getCarToRoomUseCase.getCarsFromRoom().toString() + "Jules le bozo");

            }
        }

        fun getCarFromRoom() {
            viewModelScope.launch(Dispatchers.IO) {
                println(getCarToRoomUseCase.getCarsFromRoom()[0].toString() + "Jules le bozo");
                liveDataCar.postValue(getCarToRoomUseCase.getCarsFromRoom());
            }
        }

    fun addUserToRoom(id: Int, name: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addUserToRoomUseCase.addUserToRoom(id, name, password)
            println("TEST JULES : " + getUserFromRoomUseCase.getUsersFromRoom().toString());

        }
    }

    fun getUserToRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            println(getUserFromRoomUseCase.getUsersFromRoom()[0].toString() + "Jules le bozo");

        }
    }


}