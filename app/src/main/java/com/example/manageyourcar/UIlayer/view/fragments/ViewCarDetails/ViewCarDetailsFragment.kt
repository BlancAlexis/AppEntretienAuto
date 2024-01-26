package com.example.manageyourcar.UIlayer.view.fragments.ViewCarDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.manageyourcar.UIlayer.view.activities.ui.theme.ManageYourCarTheme
import com.example.manageyourcar.UIlayer.view.fragments.LoginUser.LoginUserFragment
import com.example.manageyourcar.UIlayer.viewmodel.ViewCarDetailsViewModel
import com.example.manageyourcar.dataLayer.ListenerInternet
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewCarDetailsFragment : Fragment() {
    private val listenerInternet by inject<ListenerInternet>()
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
        Toast.makeText(requireContext(), "cccccccccccccc", Toast.LENGTH_LONG).show()
//        logUserViewModel.isConnected.observe(viewLifecycleOwner) {
//            if (it) {
//                val intent = Intent(activity, MainActivity::class.java)
//                startActivity(intent)
//            }
//        }

    }

    companion object {
        fun newInstance(): LoginUserFragment {
            return LoginUserFragment()
        }
    }
}