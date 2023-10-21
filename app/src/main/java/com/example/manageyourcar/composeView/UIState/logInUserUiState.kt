package com.example.manageyourcar.composeView.UIState

data class logInUserUiState(
    val userLogin : String?= null,
    val userPassword : String?= null,

    val userLoginError : String?= null,
    val userPasswordError : String?= null
)