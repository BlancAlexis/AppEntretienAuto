package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.manageyourcar.UIlayer.composeView.ViewCarDetailsView
import com.example.manageyourcar.UIlayer.viewmodel.ViewCarDetailsViewModel
import com.example.manageyourcar.dataLayer.ListenerInternet
import com.example.manageyourcar.databinding.ViewCarDetailsBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewCarDetailsFragment : Fragment() {
    private val listenerInternet by inject<ListenerInternet>()
    private val viewCarDetailsViewModel: ViewCarDetailsViewModel by viewModel()
    private lateinit var binding: ViewCarDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewCarDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCarDetailsViewModel.setNavController(view)
//        logUserViewModel.isConnected.observe(viewLifecycleOwner) {
//            if (it) {
//                val intent = Intent(activity, MainActivity::class.java)
//                startActivity(intent)
//            }
//        }
        binding.viewCarDetail.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                val viewCarDetailsUIState by viewCarDetailsViewModel.uiState.collectAsState()
                ViewCarDetailsView(
                    uiState = viewCarDetailsUIState,
                    onEvent = viewCarDetailsViewModel::onEvent,
                )
            }
        }
    }

    companion object {
        fun newInstance(): LoginUserFragment {
            return LoginUserFragment()
        }
    }
}