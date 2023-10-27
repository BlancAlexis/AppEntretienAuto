package com.example.manageyourcar.UIlayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manageyourcar.composeView.UIState.SignInUiState
import com.example.manageyourcar.dataRoom.useCase.user.AddUserToRoomUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class AddUserViewModel : ViewModel(), KoinComponent {
    private val addUserToRoomUseCase by inject<AddUserToRoomUseCase>()

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: UserSubscriptionEvent) {
        when (event) {
            is UserSubscriptionEvent.OnClickSendButton -> onCheckFields()
            is UserSubscriptionEvent.OnLoginChanged -> onLoginChanged(event)
            is UserSubscriptionEvent.OnPasswordChanged -> onPasswordChanged(event)
            is UserSubscriptionEvent.OnValidatePasswordChanged -> onValidateChanged(event)
        }

    }

    private fun onCheckFields() {
        if (uiState.value.userPassword.equals(uiState.value.userValidatePassword)) {
          //  addUserToRoom()
            //ID en auto non?
        }
    }

    private fun onLoginChanged(event: UserSubscriptionEvent.OnLoginChanged) {
        _uiState.update {
            it.copy(
                userLogin = event.newValue
            )
        }
    }

    private fun onPasswordChanged(event: UserSubscriptionEvent.OnPasswordChanged) {
        _uiState.update {
            it.copy(
                userPassword = event.newValue
            )
        }
    }

    private fun onValidateChanged(event: UserSubscriptionEvent.OnValidatePasswordChanged) {
        _uiState.update {
            it.copy(
                userValidatePassword = event.newValue
            )
        }
    }

    private fun addUserToRoom(id: Int, login: String, identifiant: String) {
        viewModelScope.launch {
         //   addUserToRoomUseCase.addUserToRoom(id, login, identifiant)
        }
    }

}


sealed interface UserSubscriptionEvent {
    object OnClickSendButton : UserSubscriptionEvent
    data class OnLoginChanged(val newValue: String) : UserSubscriptionEvent
    data class OnPasswordChanged(val newValue: String) : UserSubscriptionEvent
    data class OnValidatePasswordChanged(val newValue: String) : UserSubscriptionEvent
}