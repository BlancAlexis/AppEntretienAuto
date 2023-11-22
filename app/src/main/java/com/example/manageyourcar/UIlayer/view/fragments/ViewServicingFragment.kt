package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.manageyourcar.UIlayer.composeView.ServicingView
import com.example.manageyourcar.UIlayer.viewmodel.ListMaintenanceViewModel
import com.example.manageyourcar.databinding.FragmentViewServicingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewServicingFragment : Fragment() {

    private val listMaintenanceViewModel: ListMaintenanceViewModel by viewModel()
    private lateinit var binding: FragmentViewServicingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewServicingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewMaintenance.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                val maintenancesPlannedUIState by listMaintenanceViewModel.uiState.collectAsState()
                ServicingView(
                    uiState = maintenancesPlannedUIState,
                    onEvent = listMaintenanceViewModel::onEvent
                )
            }
        }
    }
    companion object {
        fun newInstance(): ViewServicingFragment {
            return ViewServicingFragment()
        }
    }
}