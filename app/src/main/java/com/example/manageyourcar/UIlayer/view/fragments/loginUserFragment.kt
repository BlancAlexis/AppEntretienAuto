package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.example.manageyourcar.UIlayer.viewmodel.UserViewModel
import com.example.manageyourcar.composeView.LoginUserView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class loginUserFragment : BottomSheetDialogFragment() {
    private val userViewModel : UserViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                LoginUserView()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.isConnect.observe(viewLifecycleOwner) { isConnect -> //Accéder à mainPage}
        }


    }
    fun logUser(password: String, login: String) {
        userViewModel.checkUserIdentifiant(login, password)
        // Connexiion du user
        // Erreur sur un champs
    }
    companion object {
        fun newInstance(): loginUserFragment {
            return loginUserFragment()
        }

    }
}