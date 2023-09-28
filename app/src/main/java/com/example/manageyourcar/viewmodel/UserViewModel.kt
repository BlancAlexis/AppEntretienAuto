package com.example.manageyourcar.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val liveDataConnect = MutableLiveData<Boolean>()
    val liveDataIsCarAdd = MutableLiveData<Boolean>()


    fun addNewUser(name: String, password: String, mail: String) {
        //Traitement de l'information puis envoie du résultat
        liveDataConnect.postValue(true)
    }

    fun addNewCarBySIV(siv: String) {
        viewModelScope.launch {
            // Requete pour vérif si voiture existe puis enregistrement room
        }
    }
        fun addNewCarByImmat(immat: String) {
            viewModelScope.launch {
                // Requete pour vérif si voiture existe puis enregistrement room

            }

    }
}

