package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.manageyourcar.UIlayer.viewmodel.AddCarViewModel
import com.example.manageyourcar.UIlayer.viewmodel.UserViewModel
import com.example.manageyourcar.composeView.AddCarView
import com.example.manageyourcar.composeView.SignInUserView
import com.example.manageyourcar.databinding.FragmentAddCarBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class addCarFragment : Fragment() {

    val addCarViewModel: AddCarViewModel by viewModel()
    private lateinit var binding: FragmentAddCarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    companion object {
        fun newInstance(): addCarFragment {
            return addCarFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addCarCompose.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                val addCarUiState by addCarViewModel.uiState.collectAsState()
                AddCarView(
                    uiState = addCarUiState,
                    onEvent = addCarViewModel::onEvent
                )
            }
        }
    }
}
/* binding.buttonFindCar.setOnClickListener {
    /* if (ding.InputNumberPlate.hasFocus()) {
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
}
}
}

private fun checkPlate(plate: String?): Boolean {
if (plate?.length == 7) {
    return true
}
return false
}

fun checkSIV(SIV: String?): Boolean {
if (SIV?.length == 17) {
    return true
}
return false
}


}*/