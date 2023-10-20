package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.example.manageyourcar.composeView.common.CustomDialog
import com.example.manageyourcar.composeView.login_ui_compose
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class loginUserFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                CustomDialog( onDismiss = {})
            }
        }
    }

    companion object {
        fun newInstance(): loginUserFragment {
            return loginUserFragment()
        }

    }
}