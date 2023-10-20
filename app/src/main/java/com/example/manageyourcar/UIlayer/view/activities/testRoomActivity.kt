package com.example.manageyourcar.UIlayer.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class testRoomActivity : AppCompatActivity() {
    val userViewModel: UserViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_room)

//        userViewModel.addCarToRoom()
//        userViewModel.addUserToRoom(1, "JUJU", "TEST")
    }
}