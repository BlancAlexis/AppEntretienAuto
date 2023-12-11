package com.example.manageyourcar.dataLayer

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.manageyourcar.UIlayer.AppApplication

class ListenerInternet {

    val mutableLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    init {
        registerInternetListener()
    }
    fun registerInternetListener() {
        val connectivityManager =
            AppApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.let {
            it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    mutableLiveData.postValue(false)
                    Log.i("TAG", "onAvailable: Connecté à internet!")
                }

                override fun onLost(network: Network) {
                    mutableLiveData.postValue(true)
                    Log.i("TAG", "onLost: Aucune connexion internet...")
                }
            })
        }
    }
}