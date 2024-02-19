package com.example.manageyourcar.UIlayer.view.fragments.ViewListMaintenance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.manageyourcar.UIlayer.view.activities.ui.theme.ManageYourCarTheme
import com.example.manageyourcar.UIlayer.viewmodel.ListMaintenanceViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class ViewListMaintenanceFragment : Fragment(), KoinComponent {
    private val listMaintenanceViewModel: ListMaintenanceViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ManageYourCarTheme {
                    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                    val maintenancesPlannedUIState by listMaintenanceViewModel.uiState.collectAsState()
                    ManageYourCarTheme {
                        ViewListMaintenanceView(
                            uiState = maintenancesPlannedUIState,
                            onEvent = listMaintenanceViewModel::onEvent
                        )
                    }
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listMaintenanceViewModel.setNavController(view)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    listMaintenanceViewModel.onBackPressed()
                    requireActivity().finish()
                }
            })

    }

    companion object {
        fun newInstance(): ViewListMaintenanceFragment {
            return ViewListMaintenanceFragment()
        }
    }

}