package com.example.manageyourcar.domainLayer.utils

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.manageyourcar.UIlayer.view.activities.OnApplicationEvent

class BroadcastReceiverInternet : BroadcastReceiver() {
    val isConnected= MutableLiveData<Boolean>()
    override fun onReceive(p0: Context?, p1: Intent?) {
        val connectivityManager =
            p0?.applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.let {
            it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isConnected.postValue(true)
                    Log.i(TAG, "onAvailable: Connecté à internet!")
                }

                override fun onLost(network: Network) {
                    isConnected.postValue(false)
                    Log.i(TAG, "onLost: Aucune connexion internet...")
                }
            })
        }    }
}