package com.example.manageyourcar.UIlayer.view.fragments.ViewCarDetails

import android.app.AlertDialog
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.manageyourcar.UIlayer.view.activities.ui.theme.ManageYourCarTheme
import com.example.manageyourcar.UIlayer.view.fragments.LoginUser.LoginUserFragment
import com.example.manageyourcar.UIlayer.viewmodel.ViewCarDetailsViewModel
import com.example.manageyourcar.dataLayer.GlobalEvent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class ViewCarDetailsFragment : Fragment(), KoinComponent, GlobalEvent {
    private val viewCarDetailsViewModel: ViewCarDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                val viewCarDetailsUIState by viewCarDetailsViewModel.uiState.collectAsState()
                ManageYourCarTheme {
                    ViewCarDetailsView(
                        uiState = viewCarDetailsUIState,
                        onEvent = viewCarDetailsViewModel::onEvent,
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCarDetailsViewModel.setNavController(view)
    }

    companion object {
        fun newInstance(): LoginUserFragment {
            return LoginUserFragment()
        }
    }

    override fun onInternetConnectionLost() {
        // Toast.makeText(AppApplication.instance, "Vous n'êtes pas connecté à internet", Toast.LENGTH_SHORT).show()
        println("Internet lost")
        var alertDialogBuilder = AlertDialog.Builder(this.context)
            .setMessage("Vous n'êtes pas connecté à internet")
            .setPositiveButton("Ok") { dialog, which -> dialog.dismiss() }
            .create()
        alertDialogBuilder.show()
    }


    override fun onInternetConnectionAvailable() {
        println("yes")
    }

    override fun onLocationChanged(location: Location) {
        println("yes")
    }
}