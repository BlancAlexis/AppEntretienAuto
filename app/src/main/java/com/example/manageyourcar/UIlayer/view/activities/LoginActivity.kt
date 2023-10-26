package com.example.manageyourcar.UIlayer.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.view.fragments.addCarFragment
import com.example.manageyourcar.UIlayer.view.fragments.addUserFragment
import com.example.manageyourcar.UIlayer.view.fragments.mapsFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainerView, mapsFragment.newInstance())
            .commit()
    }
}