package com.example.manageyourcar.composeView.UIState

data class LoginUiState (
    val userLogin : String?= "",
    val userPassword : String?= "",

    val userLoginError : String?= null,
    val userPasswordError : String?= null
)