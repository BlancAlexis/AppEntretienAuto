package com.example.manageyourcar.UIlayer.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
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
        setSupportActionBar(binding.toolbar)

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
