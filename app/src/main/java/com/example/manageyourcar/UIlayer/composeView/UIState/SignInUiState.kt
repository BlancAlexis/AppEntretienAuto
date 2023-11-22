package com.example.manageyourcar.UIlayer.composeView.UIState

data class SignInUiState(
    val userLogin: String? = "",
    val userPassword: String? = "",
    val userFirstName: String? = "",
    val userLastName: String? = "",
    val userValidatePassword: String? = "",

    val userLoginError: String? = null,
    val userPasswordError: String? = null,

    val onInternetLost: Boolean=false
) {

}