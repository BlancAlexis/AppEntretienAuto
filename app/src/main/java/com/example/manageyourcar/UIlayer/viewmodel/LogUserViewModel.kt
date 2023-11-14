package com.example.manageyourcar.UIlayer.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.UIlayer.composeView.UIState.LoginUiState
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.useCaseBusiness.LoginUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LogUserViewModel : ViewModel(), KoinComponent {

    private val logUseCase by inject<LoginUserUseCase>()
    private val cacheManagerRepository by inject<CacheManagerRepository>()
    private lateinit var navController: NavController
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            cacheManagerRepository.getUserId(AppApplication.instance.applicationContext).let {
                if (it is Ressource.Success) {
                       // NavController toujours pas ini comme dans init navController?.navigate(R.id.action_LoginUserFragment_to_viewServicingFragment)
                }
            }
        }
    }
    fun onEvent(event: UserLoginEvent) {
        when (event) {
            is UserLoginEvent.OnClickSendButton -> onTryLog()
            is UserLoginEvent.OnLoginChanged -> onLoginChanged(event)
            is UserLoginEvent.OnPasswordChanged -> onPasswordChanged(event)
            is UserLoginEvent.OnSignInButton -> navController?.navigate(R.id.action_LoginUserFragment_to_AddUserFragment)

        }
    }

     fun setNavController(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onTryLog() {
        viewModelScope.launch {
            when (val result = logUseCase.loginUser(_uiState.value.userLogin!!, _uiState.value.userPassword!!, AppApplication.instance.applicationContext)){
                is Ressource.Success -> {
                    cacheManagerRepository.putUserId(AppApplication.instance.applicationContext, 0)
                    navController?.navigate(R.id.action_LoginUserFragment_to_viewServicingFragment)
                }

                is Ressource.Error -> {
                    _uiState.update {
                        it.copy(
                            userLoginError = "Un champs ne correspond pas",
                            userPasswordError = "Un champs ne correspond pas"
                        )
                    }
                }

                is Ressource.Loading -> TODO()
            }
        }
    }

    fun onLoginChanged(event: UserLoginEvent.OnLoginChanged) {
        _uiState.update {
            it.copy(
                userLogin = event.newValue
            )
        }
    }

    fun onPasswordChanged(event: UserLoginEvent.OnPasswordChanged) {
        _uiState.update {
            it.copy(
                userPassword = event.newValue
            )
        }
    }
}

sealed interface UserLoginEvent {
    object OnClickSendButton : UserLoginEvent
    object OnSignInButton : UserLoginEvent
    data class OnLoginChanged(val newValue: String) : UserLoginEvent
    data class OnPasswordChanged(val newValue: String) : UserLoginEvent
}