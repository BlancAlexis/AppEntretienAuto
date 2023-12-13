package com.example.manageyourcar.UIlayer.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.manageyourcar.UIlayer.composeView.UIState.SignInUiState
import com.example.manageyourcar.domainLayer.useCaseRoom.user.AddUserRoomUseCase
import com.example.manageyourcar.domainLayer.utils.UserEntryChecker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class AddUserViewModel : ViewModel(), KoinComponent {
    private val addUserRoomUseCase by inject<AddUserRoomUseCase>()
    private lateinit var navController: NavController


    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()

    fun setNavController(view: View) {
        navController = Navigation.findNavController(view)
    }
    fun onEvent(event: UserSubscriptionEvent) {
        when (event) {
            is UserSubscriptionEvent.OnClickSendButton -> onCheckFields()
            is UserSubscriptionEvent.OnLoginChanged -> onLoginChanged(event)
            is UserSubscriptionEvent.OnPasswordChanged -> onPasswordChanged(event)
            is UserSubscriptionEvent.OnFirstnameChanged ->onFirstnameChanged(event)
            is UserSubscriptionEvent.OnLastNameChanged ->  onLastNameChanged(event)
            is UserSubscriptionEvent.OnConfirmPasswordChanged -> onConfirmPasswordChanged(event)
        }

    }

    private fun onConfirmPasswordChanged(event: UserSubscriptionEvent.OnConfirmPasswordChanged) {
        _uiState.update {
            it.copy(
                userValidatePasswordError = UserEntryChecker.areTwoFieldPasswordTheSame(uiState.value.userPassword,event.newValue), //Pas fou
                userValidatePassword = event.newValue
            )
        }
    }

    private fun onLastNameChanged(event: UserSubscriptionEvent.OnLastNameChanged) {
        _uiState.update {
            it.copy(
                userLastNameError = UserEntryChecker.validateLastName(event.newValue), //Pas fou
                userLastName = event.newValue
            )
        }
    }

    private fun onFirstnameChanged(event: UserSubscriptionEvent.OnFirstnameChanged) {
        _uiState.update {
            it.copy(
                userFirstNameError = UserEntryChecker.validateFirstName(event.newValue),
                userFirstName = event.newValue
            )
        }
    }

    private fun onCheckFields() {
        //SmsSender.sendSMS("dd","e")
        viewModelScope.launch(Dispatchers.IO) {
            if (uiState.value.userPassword == uiState.value.userValidatePassword) {
                addUserRoomUseCase.invoke(uiState.value.userLogin!!, uiState.value.userPassword!!, uiState.value.userFirstName!!, uiState.value.userLastName!!)
              withContext(Dispatchers.Main) {
                  navController.popBackStack()
              }
            } else {
                _uiState.update {
                    it.copy(
                        userPasswordError = "Les mots de passe ne correspondent pas",
                    )
                }
            }
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
                userPasswordError = UserEntryChecker.validatePassword(event.newValue),
                userPassword = event.newValue
            )
        }
    }



    fun onInternetLost(bool : Boolean) {
        _uiState.update {
            it.copy(
                onInternetLost = bool
            )
        }
    }

}


sealed interface UserSubscriptionEvent {
    object OnClickSendButton : UserSubscriptionEvent
    data class OnLoginChanged(val newValue: String) : UserSubscriptionEvent
    data class OnPasswordChanged(val newValue: String) : UserSubscriptionEvent
    data class OnFirstnameChanged(val newValue: String) : UserSubscriptionEvent
    data class OnLastNameChanged(val newValue: String) : UserSubscriptionEvent
    data class OnConfirmPasswordChanged(val newValue: String) : UserSubscriptionEvent
}