package com.example.manageyourcar.UIlayer.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.view.fragments.LoginUserFragment
import com.example.manageyourcar.UIlayer.view.fragments.ViewServicingFragment

class LoginActivity : AppCompatActivity(), OnApplicationEvent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainerView, ViewServicingFragment.newInstance())
            .commit()
    }
}