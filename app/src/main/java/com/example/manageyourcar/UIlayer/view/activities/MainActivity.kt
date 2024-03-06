package com.example.manageyourcar.UIlayer.view.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.manageyourcar.R
import com.example.manageyourcar.dataLayer.retrofit.util.Ressource
import com.example.manageyourcar.databinding.ActivityMainBinding
import com.example.manageyourcar.domainLayer.useCaseBusiness.LogoutUserUseCase
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    KoinComponent {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val logoutUserUseCase by inject<LogoutUserUseCase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.navigationBarColor = getColor(R.color.darkGray) // Espace sous bottomAppBar en gris
        window.statusBarColor = getColor(R.color.darkGray) // Espace au dessus de Toolbar en gris


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_login) as NavHostFragment).navController
        setupWithNavController(binding.bottomNavigationView, navController)
        binding.toolbarMain.setBackgroundColor(getColor(R.color.darkGray))
        binding.toolbarMain.title = "Manage Your Car"
        setSupportActionBar(binding.toolbarMain)
        binding.navView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawer,
            binding.toolbarMain,
            R.string.user_id,
            R.string.app_name
        )
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_logout -> {
                lifecycleScope.launch {
                    when (logoutUserUseCase.logoutUser()) {
                        is Ressource.Error -> println("error")
                        is Ressource.Loading -> println("load")
                        is Ressource.Success -> finish() //Faire une chose qui bloque si fail?
                    }
                }
            }
        }
        return true
    }


}
