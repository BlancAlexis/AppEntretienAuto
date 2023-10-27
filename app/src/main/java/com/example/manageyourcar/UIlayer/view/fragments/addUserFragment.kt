package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.manageyourcar.UIlayer.viewmodel.AddUserViewModel
import com.example.manageyourcar.composeView.SignInUserView
import com.example.manageyourcar.dataRoom.useCase.user.AddUserToRoomUseCase
import com.example.manageyourcar.databinding.FragmentAddUserBinding
import org.koin.android.ext.android.inject
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject


class addUserFragment : Fragment() {
    private lateinit var binding: FragmentAddUserBinding
    private val addUserViewModel: AddUserViewModel by lazy {
        ViewModelProvider(this).get(
            AddUserViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addUserCompose.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                val addUserUiState by addUserViewModel.uiState.collectAsState()
                SignInUserView(
                    uiState = addUserUiState,
                    onEvent = addUserViewModel::onEvent
                )
            }
        }
    }

    companion object {
        fun newInstance(): addUserFragment {
            return addUserFragment()
        }

    }
}
