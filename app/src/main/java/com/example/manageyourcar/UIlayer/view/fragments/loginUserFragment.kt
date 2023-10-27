package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.manageyourcar.UIlayer.viewmodel.LogUserViewModel
import com.example.manageyourcar.composeView.LoginUserView
import com.example.manageyourcar.databinding.FragmentLoginUserBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class loginUserFragment : BottomSheetDialogFragment() {
    private val logUserViewModel: LogUserViewModel by viewModel()
    private lateinit var binding: FragmentLoginUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logUserField.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                val loginUserUiState by logUserViewModel.uiState.collectAsState()
                LoginUserView(
                    uiState = loginUserUiState,
                    onEvent = logUserViewModel::onEvent
                )
            }
        }
    }

fun onUserConnectionTry(login: String, password: String) {}


companion object {
    fun newInstance(): loginUserFragment {
        return loginUserFragment()
    }

}
}