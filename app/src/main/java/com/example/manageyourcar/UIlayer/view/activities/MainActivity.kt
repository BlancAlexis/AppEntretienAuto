package com.example.manageyourcar.UIlayer.view.activities

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.view.fragments.addCarFragment
import com.example.manageyourcar.databinding.ActivityMainBinding
import com.example.manageyourcar.domainLayer.utils.BroadcastReceiverInternet
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myBroadcastReceiver : BroadcastReceiverInternet
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.bottomAppBar)
        setSupportActionBar(binding.toolbar)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainerView, addCarFragment.newInstance())
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

    sealed interface MyViewEvent {
        data class navigateTo(val id : Int): MyViewEvent
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcastReceiver)
    }
}


