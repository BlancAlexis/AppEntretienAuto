package com.example.manageyourcar.UIlayer.view.fragments.AddMaintenance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.manageyourcar.UIlayer.view.activities.ui.theme.ManageYourCarTheme
import com.example.manageyourcar.UIlayer.viewmodel.AddMaintenanceViewModel
import com.example.manageyourcar.dataLayer.ListenerInternet
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddMaintenanceFragment : BottomSheetDialogFragment() {
    private val listenerInternet by inject<ListenerInternet>()
    val addMaintenanceViewModel: AddMaintenanceViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                val maintenanceUiState by addMaintenanceViewModel.uiState.collectAsState()
                ManageYourCarTheme {
                    AddMaintenanceView(
                        uiState = maintenanceUiState,
                        onEvent = addMaintenanceViewModel::onEvent
                    )
                }
            }
        }
        }

    companion object {
        fun newInstance(): AddMaintenanceFragment {
            return newInstance()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMaintenanceViewModel.isMaintenanceAdd.observe(viewLifecycleOwner) {
            if (it) {
                dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        listenerInternet.mutableLiveData.observe(viewLifecycleOwner) {
            addMaintenanceViewModel.onInternetLost(it)
        }
    }
}
