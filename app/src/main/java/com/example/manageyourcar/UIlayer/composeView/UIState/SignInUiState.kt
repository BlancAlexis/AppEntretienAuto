package com.example.manageyourcar.UIlayer.composeView.UIState

data class SignInUiState(
    val userLogin: String? = "",
    val userPassword: String? = "",
    val userValidatePassword: String? = "",

    val userLoginError: String? = null,
    val userPasswordError: String? = null
)