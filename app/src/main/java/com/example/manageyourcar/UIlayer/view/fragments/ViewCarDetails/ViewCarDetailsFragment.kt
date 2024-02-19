package com.example.manageyourcar.UIlayer.view.fragments.ViewCarDetails

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
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class ViewCarDetailsFragment : Fragment(), KoinComponent {
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
}