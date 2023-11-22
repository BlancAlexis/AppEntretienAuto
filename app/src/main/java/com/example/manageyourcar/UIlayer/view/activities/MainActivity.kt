package com.example.manageyourcar.UIlayer.view.activities

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.manageyourcar.R
import com.example.manageyourcar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.bottomAppBar)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_specification -> {
                    true
                }

                R.id.action_home -> {
                    true
                }

                R.id.action_manage -> {
                    true
                }

                else -> {
                    true
                }
            }
        }
    }
}
