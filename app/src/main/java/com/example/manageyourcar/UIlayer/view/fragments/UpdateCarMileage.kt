package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.navArgs
import com.example.manageyourcar.UIlayer.composeView.common.CustomDialogKM
import com.example.manageyourcar.UIlayer.viewmodel.UpdateCarViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateCarMileage : BottomSheetDialogFragment() {
    private val updateCarMileageViewModel: UpdateCarViewModel by viewModel()

    val args : UpdateCarMileageArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.myArg?.let {
           updateCarMileageViewModel.setCar(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {


            setContent {
                val UpdateMileageCarDetailsUIState by updateCarMileageViewModel.uiState.collectAsState()
                CustomDialogKM(UpdateMileageCarDetailsUIState = UpdateMileageCarDetailsUIState, onEvent = updateCarMileageViewModel::onEvent)
            }  }
        }
}