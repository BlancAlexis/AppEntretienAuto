package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.manageyourcar.R
import com.example.manageyourcar.databinding.FragmentAddCarBinding
import com.example.manageyourcar.databinding.FragmentAddUserBinding
import com.example.manageyourcar.UIlayer.viewmodel.UserViewModel
import com.example.manageyourcar.domainLayer.useCaseRetrofit.GetVehiculeByNetworkUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.AddCarToRoomUseCase
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject


class addCarFragment : Fragment() {

    val userViewModel: UserViewModel by viewModel()
    private lateinit var binding: FragmentAddCarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        userViewModel.addCarToRoom()
        userViewModel.getCarToRoom()
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {
        fun newInstance(): addCarFragment {
            return addCarFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFindCar.setOnClickListener {
            /* if (binding.InputNumberPlate.hasFocus()) {
                if (!binding.InputSIV.text.isNullOrEmpty()) {
                    userViewModel.addNewCarBySIV(binding.InputSIV.text.toString())
                }

            }
            if (binding.textInputLayout.hasFocus()) {
                if (!binding.InputNumberPlate.text.isNullOrEmpty()) {
                    userViewModel.addNewCarByImmat(binding.InputNumberPlate.text.toString())
                }
            }
        }

        binding.InputNumberPlate.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                if(!checkPlate(binding.InputNumberPlate.toString()))
                    binding.InputSIV.error="Numéro de plaque d'immatriculation invalide"
                binding.InputNumberPlate.setBackgroundColor(Color.RED)
            }
        }

        binding.InputSIV.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                if(!checkSIV(binding.InputSIV.toString()))
                    binding.InputSIV.error="Numéro SIV non-valide"
                    binding.InputSIV.setBackgroundColor(Color.RED)
            }
        }
    }*/
        }
    }

    private fun checkPlate(plate: String?): Boolean {
          if(plate?.length==7){
              return true
          }
        return false
    }

    fun checkSIV(SIV : String?) : Boolean {
        if (SIV?.length==17){
            return true
        }
        return false
    }


}