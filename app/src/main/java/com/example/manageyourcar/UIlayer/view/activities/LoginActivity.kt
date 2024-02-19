package com.example.manageyourcar.UIlayer.view.activities

import android.app.AlertDialog
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.manageyourcar.R
import com.example.manageyourcar.dataLayer.GlobalEvent
import com.example.manageyourcar.dataLayer.ListenerInternet
import com.example.manageyourcar.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), GlobalEvent {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.navigationBarColor = getColor(R.color.primaryColor)

        ListenerInternet.registerInternetListener(this)
    }

    override fun onInternetConnectionLost() {
        var alertDialogBuilder = AlertDialog.Builder(this)
            .setMessage("Vous n'êtes pas connecté à internet")
            .setPositiveButton("Ok") { dialog, which -> dialog.dismiss() }
            .setCancelable(false)
            .create()
        alertDialogBuilder.show()
    }

    override fun onInternetConnectionAvailable() {
        println()
    }

    override fun onLocationChanged(location: Location) {
        println()
    }
}