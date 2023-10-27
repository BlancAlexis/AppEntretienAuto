package com.example.manageyourcar.UIlayer.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.view.fragments.addUserFragment
import com.example.manageyourcar.UIlayer.view.fragments.loginUserFragment
import com.example.manageyourcar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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

        binding.bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_history -> {
                    Log.i("prout","prout")
                    true
                }
                R.id.action_home -> {
                    Log.i("prout","prout")
                    true
                }
                R.id.action_search -> {
                    Log.i("pout","pout")
                    true
                }
                else -> false
            }
        }
    }


}

