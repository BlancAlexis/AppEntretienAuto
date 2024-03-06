package com.example.manageyourcar.UIlayer.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.manageyourcar.UIlayer.UIState.SignInUiState
import com.example.manageyourcar.UIlayer.viewEvent.UIUtil
import com.example.manageyourcar.domainLayer.useCaseRoom.user.AddUserRoomUseCase
import com.example.manageyourcar.domainLayer.utils.UserEntryChecker
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class AddUserViewModel constructor(private val uiUtil: UIUtil) : ViewModel(), KoinComponent {
    private val addUserRoomUseCase by inject<AddUserRoomUseCase>()
    private lateinit var navController: NavController


    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    fun setNavController(view: View) {
        navController = Navigation.findNavController(view)
    }

    fun onEvent(event: UserSubscriptionEvent) {
        when (event) {
            is UserSubscriptionEvent.OnClickSendButton -> addUserLocalStorage()
            is UserSubscriptionEvent.OnIdentifiantChanged -> onLoginChanged(event)
            is UserSubscriptionEvent.OnPasswordChanged -> onPasswordChanged(event)
            is UserSubscriptionEvent.OnFirstnameChanged -> onFirstnameChanged(event)
            is UserSubscriptionEvent.OnLastNameChanged -> onLastNameChanged(event)
            is UserSubscriptionEvent.OnConfirmPasswordChanged -> onConfirmPasswordChanged(event)
            UserSubscriptionEvent.OnBackIconClicked -> navController.popBackStack()
        }

    }

    private fun onConfirmPasswordChanged(event: UserSubscriptionEvent.OnConfirmPasswordChanged) {
        _uiState.update {
            it.copy(
                userValidatePasswordError = UserEntryChecker.areTwoFieldPasswordTheSame(
                    uiState.value.userPassword, event.newConfirmePassword
                ),
                userValidatePassword = event.newConfirmePassword
            )
        }
    }

    private fun onLastNameChanged(event: UserSubscriptionEvent.OnLastNameChanged) {
        _uiState.update {
            it.copy(
                userLastNameError = UserEntryChecker.validateLastName(event.newLastname),
                userLastName = event.newLastname
            )
        }
    }

    private fun onFirstnameChanged(event: UserSubscriptionEvent.OnFirstnameChanged) {
        _uiState.update {
            it.copy(
                userFirstNameError = UserEntryChecker.validateFirstName(event.newFirstname),
                userFirstName = event.newFirstname
            )
        }
    }

    private fun addUserLocalStorage() {
        viewModelScope.launch(ioDispatcher) {
            if ((uiState.value.userPassword == uiState.value.userValidatePassword) && uiState.value.userPassword != "" && uiState.value.userLogin != "" && uiState.value.userFirstName != "" && uiState.value.userLastName != "") {
                addUserRoomUseCase.invoke(
                    uiState.value.userLogin,
                    uiState.value.userPassword,
                    uiState.value.userFirstName,
                    uiState.value.userLastName
                )
                withContext(Dispatchers.Main) {
                    navController.popBackStack()
                }
            } else {
                withContext(Dispatchers.Main) {
                    uiUtil.displayToastSuspend("Le formulaire contient une erreur")
                }
            }
        }

    }

    private fun onLoginChanged(event: UserSubscriptionEvent.OnIdentifiantChanged) {
        _uiState.update {
            it.copy(
                userLogin = event.newIdentifiant
            )
        }
    }

    private fun onPasswordChanged(event: UserSubscriptionEvent.OnPasswordChanged) {
        _uiState.update {
            it.copy(
                userPasswordError = UserEntryChecker.validatePassword(event.newPassword),
                userPassword = event.newPassword
            )
        }
    }
}


sealed interface UserSubscriptionEvent {
    object OnBackIconClicked : UserSubscriptionEvent
    object OnClickSendButton : UserSubscriptionEvent
    data class OnIdentifiantChanged(val newIdentifiant: String) : UserSubscriptionEvent
    data class OnPasswordChanged(val newPassword: String) : UserSubscriptionEvent
    data class OnFirstnameChanged(val newFirstname: String) : UserSubscriptionEvent
    data class OnLastNameChanged(val newLastname: String) : UserSubscriptionEvent
    data class OnConfirmPasswordChanged(val newConfirmePassword: String) : UserSubscriptionEvent
}