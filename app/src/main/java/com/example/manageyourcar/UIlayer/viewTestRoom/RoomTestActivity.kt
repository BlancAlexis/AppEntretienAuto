package com.example.manageyourcar.UIlayer.viewTestRoom

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomTestActivity : AppCompatActivity() {

    val userViewModel: UserViewModel by viewModel()
    lateinit var txt: TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_test)

        //userViewModel.getCarFromRoom();
        print("JULES");

    }


}