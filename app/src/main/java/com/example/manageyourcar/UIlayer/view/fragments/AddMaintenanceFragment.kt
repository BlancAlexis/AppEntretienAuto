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
import com.example.manageyourcar.dataLayer.ListenerInternet
import com.example.manageyourcar.databinding.FragmentAddMaintenanceCarBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddMaintenanceFragment : BottomSheetDialogFragment() {
    private val listenerInternet by inject<ListenerInternet>()
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
        addMaintenanceViewModel.isMaintenanceAdd.observe(viewLifecycleOwner) {
            if (it) {
                dismiss()
            }
        }
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

    override fun onResume() {
        super.onResume()
        listenerInternet.mutableLiveData.observe(viewLifecycleOwner) {
            addMaintenanceViewModel.onInternetLost(it)
        }
    }
}

