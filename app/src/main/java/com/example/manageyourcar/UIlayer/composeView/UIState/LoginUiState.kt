package com.example.manageyourcar.UIlayer.composeView.UIState

data class LoginUiState(
    val userLogin: String? = "",
    val userPassword: String? = "",

    val userLoginError: String? = null,
    val userPasswordError: String? = null
)