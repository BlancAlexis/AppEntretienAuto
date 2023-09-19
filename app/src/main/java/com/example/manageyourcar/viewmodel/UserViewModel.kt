package com.example.manageyourcar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
     lateinit var liveDataConnect : MutableLiveData<Boolean>

fun addNewUser(name : String, password : String, mail : String){}
}

fun getConnectLiveData() : MutableLiveData<Boolean>{
    return liveDataConnect
}