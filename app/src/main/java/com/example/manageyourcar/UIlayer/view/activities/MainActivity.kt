package com.example.manageyourcar.UIlayer.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.view.fragments.addCarFragment
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

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainerView, addCarFragment.newInstance())
            .commit()

    }

}


