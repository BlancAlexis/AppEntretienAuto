package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.manageyourcar.composeView.UIState.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LogUserViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event : UserLoginEvent) {
        when(event){
            is UserLoginEvent.OnClickSendButton -> TODO()
            is UserLoginEvent.OnLoginChanged -> TODO()
            is UserLoginEvent.OnPasswordChanged -> TODO()
        }
    }

    fun onLoginChanged(event : UserLoginEvent.OnLoginChanged){
        _uiState.update {
            it.copy(
                userLogin = event.newValue
            )
        }
    }
    fun onPasswordChanged(event : UserLoginEvent.OnPasswordChanged){
        _uiState.update {
            it.copy(
                userPassword = event.newValue
            )
        }
    }
}
sealed interface UserLoginEvent {
    object OnClickSendButton : UserLoginEvent
    data class OnLoginChanged(val newValue: String) : UserLoginEvent
    data class OnPasswordChanged(val newValue: String) : UserLoginEvent
}