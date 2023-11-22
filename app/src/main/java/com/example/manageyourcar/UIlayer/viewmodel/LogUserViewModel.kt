package com.example.manageyourcar.UIlayer.viewmodel

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.UIlayer.composeView.UIState.LoginUiState
import com.example.manageyourcar.UIlayer.view.activities.MainActivity
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.dataLayer.model.MaintenanceService
import com.example.manageyourcar.dataLayer.model.MaintenanceServiceType
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.useCaseBusiness.LoginUserUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.AddCarToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.AddCarMaintenanceUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.GetAllUserMaintenanceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.Instant
import java.util.Date

class LogUserViewModel : ViewModel(), KoinComponent {

    private val logUseCase by inject<LoginUserUseCase>()
    private val cacheManagerRepository by inject<CacheManagerRepository>()
    private lateinit var navController: NavController
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: UserLoginEvent) {
        when (event) {
            is UserLoginEvent.OnClickSendButton -> {
                onTryLog()
            }

            is UserLoginEvent.OnLoginChanged -> onLoginChanged(event)
            is UserLoginEvent.OnPasswordChanged -> onPasswordChanged(event)
            is UserLoginEvent.OnSignInButton -> navController?.navigate(R.id.action_LoginUserFragment_to_AddUserFragment)

        }
    }

    fun setNavController(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    navController = Navigation.findNavController(view)
        //onTryLog()
    }

    private fun onTryLog() {
        viewModelScope.launch {
            when (val result = logUseCase.loginUser(_uiState.value.userLogin!!, _uiState.value.userPassword!!, AppApplication.instance.applicationContext)) {
                is Ressource.Success -> {
                    cacheManagerRepository.putUserId(AppApplication.instance.applicationContext, result.data!!)
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