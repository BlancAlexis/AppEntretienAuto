package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.manageyourcar.UIlayer.composeView.BluetoothDeviceView
import com.example.manageyourcar.UIlayer.viewmodel.BluetoothViewModel
import com.example.manageyourcar.databinding.FragmentConnectObdBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConnectObdFragment : Fragment() {
    val connectObdViewModel: BluetoothViewModel by viewModel()
    private lateinit var binding: FragmentConnectObdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        connectObdViewModel.startScan()
        binding = FragmentConnectObdBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        fun newInstance(): ConnectObdFragment {
            return newInstance()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listOBDDevice.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                val bluetoothUiState by connectObdViewModel.state.collectAsState()
                BluetoothDeviceView(
                    uiState = bluetoothUiState,
                    onEvent = connectObdViewModel::onEvent
                )
            }
        }
    }
}