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
import com.example.manageyourcar.UIlayer.view.fragments.AddUserFragment
import com.example.manageyourcar.UIlayer.view.fragments.MapsFragment
import com.example.manageyourcar.UIlayer.view.fragments.ViewServicingFragment
import com.example.manageyourcar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.bottomAppBar)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.nav_host_fragment, ViewServicingFragment.newInstance())
            .commit()


        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_specification -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, ViewServicingFragment.newInstance())
                        .commit()
                    true
                }

                R.id.action_home -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, MapsFragment.newInstance())
                        .commit()
                    true
                }

                R.id.action_manage -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, ViewServicingFragment.newInstance())
                        .commit()
                    true
                }

                else -> {
                    true
                }
            }
        }
    }
}
