package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.manageyourcar.UIlayer.composeView.AddCarView
import com.example.manageyourcar.UIlayer.viewmodel.AddCarViewModel
import com.example.manageyourcar.dataLayer.ListenerInternet
import com.example.manageyourcar.databinding.FragmentAddCarBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddCarFragment : Fragment() {
    private val listenerInternet by inject<ListenerInternet>()


    val addCarViewModel: AddCarViewModel by viewModel()
    private lateinit var binding: FragmentAddCarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCarBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        fun newInstance(): AddCarFragment {
            return AddCarFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addCarCompose.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                val addCarUiState by addCarViewModel.uiState.collectAsState()
                AddCarView(
                    uiState = addCarUiState,
                    onEvent = addCarViewModel::onEvent
                )
            }
        }
    }
    override fun onResume() {
        super.onResume()
        listenerInternet.mutableLiveData.observe(viewLifecycleOwner){
            addCarViewModel.onInternetLost(it)
        }
        addCarViewModel.dismissFragment.observe(viewLifecycleOwner){
            if(it){
                findNavController().popBackStack()
            }
        }
    }
}
