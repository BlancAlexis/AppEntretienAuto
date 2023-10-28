package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.manageyourcar.UIlayer.composeView.AddCarView
import com.example.manageyourcar.UIlayer.viewmodel.AddCarViewModel
import com.example.manageyourcar.databinding.FragmentAddCarBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class addCarFragment : Fragment() {

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
        fun newInstance(): addCarFragment {
            return addCarFragment()
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
}
