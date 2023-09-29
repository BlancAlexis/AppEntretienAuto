package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.dataLayer.util.Ressource
import com.example.manageyourcar.domainLayer.useCase.GetVehiculeByImmatUseCase
import com.example.manageyourcar.domainLayer.useCase.GetVehiculeByNetworkUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserViewModel : ViewModel(), KoinComponent {
    val liveDataConnect = MutableLiveData<Boolean>()
    val liveDataIsCarAdd = MutableLiveData<Boolean>()
    val getVehiculeByNetworkUseCase by inject<GetVehiculeByNetworkUseCase>()
    val getVehiculeByImmatUseCase by inject<GetVehiculeByImmatUseCase>()



    fun addNewUser(name: String, password: String, mail: String) {
        //Traitement de l'information puis envoie du résultat
        liveDataConnect.postValue(true)
    }

    fun addNewCarBySIV(SIV: String) {
        viewModelScope.launch {
            getVehiculeByNetworkUseCase.getVehiculeBySiv(SIV).collect { result ->
                when (result) {
                    is Ressource.Loading-> {
                        println("load")
                    }
                    is Ressource.Error -> {
                        println("Ressource.Error"+result.message)
                        // Faire une classe gestion erreur
                    }
                    is Ressource.Success -> {
                        println("Ressource.Success"+result.data)
                        // Requete pour vérif si voiture existe puis enregistrement room

                    }
                }
            }
        }
    }
        fun addNewCarByImmat(immat: String) {
            viewModelScope.launch {
                getVehiculeByImmatUseCase.getVehiculeByImmat("CP-370-YK").collect { result ->
                    when (result) {
                        is Ressource.Loading-> {
                            println("load")
                        }
                        is Ressource.Error -> {
                            println("Ressource.Error"+result.message)
                            // Faire une classe gestion erreur
                        }
                        is Ressource.Success -> {
                            println("Ressource.Success"+result.data)
                            // Requete pour vérif si voiture existe puis enregistrement room

                        }
                    }
                }
                // Requete pour vérif si voiture existe puis enregistrement room

            }

    }
}

