package com.example.manageyourcar.UIlayer.view.fragments.UpdateCarMileage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.navArgs
import com.example.manageyourcar.UIlayer.view.common.CustomDialogKM
import com.example.manageyourcar.UIlayer.view.activities.ui.theme.ManageYourCarTheme
import com.example.manageyourcar.UIlayer.viewmodel.UpdateCarMileageViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateCarMileageFragment : BottomSheetDialogFragment() {
    private val updateCarMileageViewModel: UpdateCarMileageViewModel by viewModel()

    val args: UpdateCarMileageFragmentArgs by navArgs()


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
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ManageYourCarTheme {
                    val updateMileageCarDetailsUIState by updateCarMileageViewModel.uiState.collectAsState()
                CustomDialogKM(
                    uiState = updateMileageCarDetailsUIState,
                    onEvent = updateCarMileageViewModel::onEvent
                )
               }
            }
        }
    }
}