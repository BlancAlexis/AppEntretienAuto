package com.example.manageyourcar.UIlayer.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.manageyourcar.R
import com.example.manageyourcar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_login) as NavHostFragment).navController
        setSupportActionBar(binding.bottomAppBar)
        setupWithNavController(binding.bottomNavigationView, navController)
    }
}
