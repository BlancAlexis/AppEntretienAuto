package com.example.manageyourcar.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
       val liveDataConnect = MutableLiveData<Boolean>()



fun addNewUser(name : String, password : String, mail : String){
    //Traitement de l'information puis envoie du résultat
    liveDataConnect.postValue(true)
}
}

