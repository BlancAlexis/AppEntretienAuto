package com.example.manageyourcar.UIlayer.view.fragments.AddUser

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
import com.example.manageyourcar.UIlayer.viewmodel.AddUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent


class AddUserFragment : Fragment(), KoinComponent {
    private val addUserViewModel: AddUserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ManageYourCarTheme {
                    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                    val addUserUiState by addUserViewModel.uiState.collectAsState()
                    ManageYourCarTheme {
                        AddUserView(
                            uiState = addUserUiState, onEvent = addUserViewModel::onEvent
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

    companion object {
        fun newInstance(): AddUserFragment {
            return AddUserFragment()
        }

    }
}
