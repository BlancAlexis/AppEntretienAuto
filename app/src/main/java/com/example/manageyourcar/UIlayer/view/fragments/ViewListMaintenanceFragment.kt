package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.manageyourcar.UIlayer.composeView.MaintenanceListView
import com.example.manageyourcar.UIlayer.viewmodel.ListMaintenanceViewModel
import com.example.manageyourcar.dataLayer.ListenerInternet
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.databinding.FragmentViewServicingBinding
import com.example.manageyourcar.domainLayer.useCaseBusiness.LogoutUserUseCase
import kotlinx.coroutines.coroutineScope
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Date
import kotlin.coroutines.coroutineContext

class ViewListMaintenanceFragment : Fragment() {
    private val listMaintenanceViewModel: ListMaintenanceViewModel by viewModel()
    private val listenerInternet by inject<ListenerInternet>()
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
        listMaintenanceViewModel.setNavController(view)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    listMaintenanceViewModel.onBackPressed()
                    requireActivity().finish()
                }
            })
        binding.viewMaintenance.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                val maintenancesPlannedUIState by listMaintenanceViewModel.uiState.collectAsState()
                MaintenanceListView(
                    uiState = maintenancesPlannedUIState,
                    onEvent = listMaintenanceViewModel::onEvent
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        listenerInternet.mutableLiveData.observe(viewLifecycleOwner){
            listMaintenanceViewModel.onInternetLost(it)
        }
    }

    companion object {
        fun newInstance(): ViewListMaintenanceFragment {
            return ViewListMaintenanceFragment()
        }
    }
}