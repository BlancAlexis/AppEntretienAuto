package com.example.manageyourcar.UIlayer.view.fragments.ConnectOBD

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.manageyourcar.UIlayer.view.activities.ui.theme.ManageYourCarTheme
import com.example.manageyourcar.UIlayer.viewmodel.ConnectObdViewModel
import com.example.manageyourcar.dataLayer.GlobalEvent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class ConnectObdFragment : Fragment(), KoinComponent, GlobalEvent {
    val connectObdViewModel: ConnectObdViewModel by viewModel()


    private val bluetoothManager by lazy {
        requireActivity().getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }
    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        connectObdViewModel.startScan()
        return ComposeView(requireContext()).apply {
            setContent {
                ManageYourCarTheme {
                    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                    val bluetoothUiState by connectObdViewModel.state.collectAsState()
                    ManageYourCarTheme {
                        ConnectObdView(
                            uiState = bluetoothUiState,
                            onEvent = connectObdViewModel::onEvent
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(): ConnectObdFragment {
            return newInstance()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val enableBluetoothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { /* Not needed */ }

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { perms ->
            val canEnableBluetooth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                perms[Manifest.permission.BLUETOOTH_CONNECT] == true
            } else true

            if (canEnableBluetooth && !isBluetoothEnabled) {
                enableBluetoothLauncher.launch(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                )
            }
        }
        val c=arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionLauncher.launch(
                c
            )
        }
    }
    private fun hasPermissions(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun allPermissionsGranted(permissions: MutableMap<String, Boolean>): Boolean {
        return permissions.values.all { it }
    }

    override fun onInternetConnectionLost() {
        TODO("Not yet implemented")
    }

    override fun onInternetConnectionAvailable() {
        TODO("Not yet implemented")
    }

    override fun onLocationChanged(location: Location) {
        TODO("Not yet implemented")
    }


}