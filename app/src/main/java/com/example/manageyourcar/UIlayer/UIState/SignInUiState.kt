package com.example.manageyourcar.UIlayer.UIState

data class SignInUiState(
    val userLogin: String = "",
    val userPassword: String = "",
    val userFirstName: String = "",
    val userLastName: String = "",
    val userValidatePassword: String = "",

    val userLoginError: String = "",
    val userPasswordError: String = "",
    val userFirstNameError: String = "",
    val userLastNameError: String = "",
    val userValidatePasswordError: String = "",
    )

