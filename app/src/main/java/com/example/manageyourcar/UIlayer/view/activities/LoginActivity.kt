package com.example.manageyourcar.UIlayer.view.activities

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.view.fragments.loginUserFragment
import com.example.manageyourcar.domainLayer.utils.BroadcastReceiverInternet

class LoginActivity : AppCompatActivity(), OnApplicationEvent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainerView, loginUserFragment.newInstance())
            .commit()

        val myBroadcastReceiver = BroadcastReceiverInternet()
        registerReceiver(myBroadcastReceiver, IntentFilter())
        myBroadcastReceiver.isConnected.observe(this){
            when(it){
                true -> Log.i("doe","connecté")
                false -> Log.i("doe","déconnecté")
            }
        }
    }



}