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
import com.example.manageyourcar.dataLayer.model.Car
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.useCaseBusiness.LoginUserUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.AddCarToRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetCarsFromRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LogUserViewModel : ViewModel(), KoinComponent {
    //Test, à supprimer
    private val addCarToRoomUseCase by inject<AddCarToRoomUseCase>()
    private val getCarsFromRoomUseCase by inject<GetUserCarsUseCase>()

    private val logUseCase by inject<LoginUserUseCase>()
    private val cacheManagerRepository by inject<CacheManagerRepository>()
    private lateinit var navController: NavController
    private val _uiState = MutableStateFlow(LoginUiState())
    private var isLog : Boolean = false
    val uiState = _uiState.asStateFlow()

    fun mdr() {
        onTryLog()
        viewModelScope.launch(Dispatchers.IO) {
        addCarToRoomUseCase.addCarToRoom(Car(1,"Peugeot", "206", 2000, "100000", "0", "0", 2, 2, 2, 2, ownerID = null))
          getCarsFromRoomUseCase.invoke().collect(){
            when(val result= it){
            is Ressource.Error -> println(result.error?.localizedMessage)
              is Ressource.Loading -> println("Load")
              is Ressource.Success -> println(result.data.toString())
          }
    }}
    }

/*    init {
        viewModelScope.launch {
            cacheManagerRepository.getUserId(AppApplication.instance.applicationContext).let {
                if (it is Ressource.Success) {
                    isLog=true
                       //navController?.navigate(R.id.action_LoginUserFragment_to_viewServicingFragment)
                }
            }
        }
    }*/
    fun onEvent(event: UserLoginEvent) {
        when (event) {
            is UserLoginEvent.OnClickSendButton ->{
                mdr()
                //onTryLog()
            }
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
                   // navController?.navigate(R.id.action_LoginUserFragment_to_viewServicingFragment)
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