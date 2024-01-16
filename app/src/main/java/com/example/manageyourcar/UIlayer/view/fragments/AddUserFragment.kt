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
import com.example.manageyourcar.UIlayer.composeView.SignInUserView
import com.example.manageyourcar.UIlayer.view.activities.ui.theme.ManageYourCarTheme
import com.example.manageyourcar.UIlayer.viewmodel.AddUserViewModel
import com.example.manageyourcar.dataLayer.ListenerInternet
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddUserFragment : Fragment() {
    private val listenerInternet by inject<ListenerInternet>()
    private val addUserViewModel: AddUserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ManageYourCarTheme {
                    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                    val addUserUiState by addUserViewModel.uiState.collectAsState()
                    ManageYourCarTheme {
                        SignInUserView(
                            uiState = addUserUiState,
                            onEvent = addUserViewModel::onEvent
                        )
                    }
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addUserViewModel.setNavController(view)
    }

    override fun onResume() {
        super.onResume()
        listenerInternet.mutableLiveData.observe(viewLifecycleOwner) {
            addUserViewModel.onInternetLost(it)
        }
    }

    companion object {
        fun newInstance(): AddUserFragment {
            return AddUserFragment()
        }

    }
}
