package com.example.manageyourcar.UIlayer.view.fragments.LoginUser

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.manageyourcar.UIlayer.view.activities.MainActivity
import com.example.manageyourcar.UIlayer.view.activities.ui.theme.ManageYourCarTheme
import com.example.manageyourcar.UIlayer.viewmodel.LoginUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class LoginUserFragment : Fragment(), KoinComponent {
    private val logUserViewModel: LoginUserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                ManageYourCarTheme {
                    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                    val loginUserUiState by logUserViewModel.uiState.collectAsState()
                    ManageYourCarTheme {
                        LoginUserView(
                            uiState = loginUserUiState, onEvent = logUserViewModel::onEvent
                        )
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logUserViewModel.setNavController(view)
        logUserViewModel.isConnected.observe(viewLifecycleOwner) {
            if (it) {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    companion object {
        fun newInstance(): LoginUserFragment {
            return LoginUserFragment()
        }
    }
}