package com.example.manageyourcar.UIlayer.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.view.fragments.AddCarFragment
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
            .add(R.id.fragContainerView, AddCarFragment.newInstance())
            .commit()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                 R.id.action_specification -> {
                 Log.i("e","gfgd")
                true }
                R.id.action_home -> {
                    Log.i("e","gffdddd")
                    true
                }
                R.id.action_manage -> {
                    Log.i("e","vvvvvvv")
                    true
                }

                else -> {
                    true
                }
            }
        }
    }

}


