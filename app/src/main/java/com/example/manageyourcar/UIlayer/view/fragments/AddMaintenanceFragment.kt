package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.manageyourcar.UIlayer.composeView.AddMaintenanceView
import com.example.manageyourcar.UIlayer.viewmodel.AddMaintenanceViewModel
import com.example.manageyourcar.dataLayer.model.MaintenanceService
import com.example.manageyourcar.databinding.FragmentAddMaintenanceCarBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddMaintenanceFragment : BottomSheetDialogFragment() {
    val addMaintenanceViewModel: AddMaintenanceViewModel by viewModel()
    private lateinit var binding: FragmentAddMaintenanceCarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddMaintenanceCarBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        fun newInstance(): AddMaintenanceFragment {
            return newInstance()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addMaintenanceCompose.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                val maintenanceUiState by addMaintenanceViewModel.uiState.collectAsState()
                AddMaintenanceView(
                    uiState = maintenanceUiState,
                    onEvent = addMaintenanceViewModel::onEvent
                )
            }
        }
    }
}

sealed interface onMaintenanceEvent{
    object onValidatePressed : onMaintenanceEvent
    data class onDateChanged (val newDate : String): onMaintenanceEvent
    data class onMileageChanged( val newValue: String) : onMaintenanceEvent
    data class onMaintenanceChanged(val newValue: MaintenanceService) : onMaintenanceEvent
    data class onCarChanged( val newValue: String) : onMaintenanceEvent

}