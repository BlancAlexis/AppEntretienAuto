package com.example.manageyourcar.UIlayer.view.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.view.fragments.addUserFragment
import com.example.manageyourcar.UIlayer.view.fragments.loginUserFragment
import com.example.manageyourcar.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        val view = binding.root;
        setContentView(view);

        setSupportActionBar(binding.bottomAppBar)
        setSupportActionBar(binding.toolbar)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainerView, loginUserFragment.newInstance())
            .commit()

    }

}


