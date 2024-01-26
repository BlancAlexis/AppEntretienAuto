package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.manageyourcar.UIlayer.composeView.AddCarView
import com.example.manageyourcar.UIlayer.view.activities.ui.theme.ManageYourCarTheme
import com.example.manageyourcar.UIlayer.viewmodel.AddCarViewModel
import com.example.manageyourcar.dataLayer.ListenerInternet
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddCarFragment : Fragment() {
    private val listenerInternet by inject<ListenerInternet>()

    val addCarViewModel: AddCarViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ManageYourCarTheme {
                    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                    val addCarUiState by addCarViewModel.uiState.collectAsState()
                    ManageYourCarTheme {
                        AddCarView(
                            uiState = addCarUiState,
                            onEvent = addCarViewModel::onEvent
                        )
                    }
                }
            }
        }
        }

    companion object {
        fun newInstance(): AddCarFragment {
            return AddCarFragment()
        }
    }


    override fun onResume() {
        super.onResume()
        listenerInternet.mutableLiveData.observe(viewLifecycleOwner) {
            addCarViewModel.onInternetLost(it)
        }
        addCarViewModel.dismissFragment.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }
    }
}
