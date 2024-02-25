package com.example.manageyourcar.UIlayer.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.UIState.ViewCarDetailsState
import com.example.manageyourcar.UIlayer.UIUtil
import com.example.manageyourcar.UIlayer.view.fragments.ViewCarDetails.ViewCarDetailsFragmentDirections
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.useCaseRoom.car.DeleteCarRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

class ViewCarDetailsViewModel(
    private val cacheManagerRepository: CacheManagerRepository, private val uiUtil: UIUtil
) : ViewModel(), KoinComponent {
    private val getUserCarsUseCase by inject<GetUserCarsUseCase>()
    private val deleteCarRoomUseCase by inject<DeleteCarRoomUseCase>()
    private val _uiState = MutableStateFlow<ViewCarDetailsState>(ViewCarDetailsState.Loading)
    val uiState = _uiState.asStateFlow()
    private lateinit var navController: NavController

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    init {
        getCachedUser()
    }

    private fun getCachedUser() {
        viewModelScope.launch(ioDispatcher) {
            getUserCarsUseCase.invoke().collect { result ->
                when (result) {
                    is Ressource.Error -> uiUtil.displayToastSuspend(
                        result.error?.localizedMessage
                            ?: "Erreur lors de la récupération de vos voitures"
                    )

                    is Ressource.Success -> result.data?.let {
                        updateListCar(it)
                        cacheManagerRepository.saveUserCarList(it)
                    }

                    else -> {}
                }
            }
        }
    }

    fun onEvent(event: ViewCarDetailsEvent) {
        when (event) {
            is ViewCarDetailsEvent.OnClickAddCarButton -> {
                navController.navigate(R.id.action_viewCarDetailsFragment_to_AddCarFragment)
            }

            is ViewCarDetailsEvent.OnUpdateMileage -> {
                viewModelScope.launch() {
                    when (val result = cacheManagerRepository.getUserCarList()) {
                        is Ressource.Error -> {
                            uiUtil.displayToastSuspend("Erreur lors de la récupération des voitures")
                            return@launch
                        }

                        is Ressource.Loading -> {
                            uiUtil.displayToastSuspend("Chargement des voitures")
                            return@launch
                        }

                        is Ressource.Success -> {
                            if (result.data.isNullOrEmpty()) {
                                uiUtil.displayToastSuspend("Vous n'avez encore aucune voiture")
                                return@launch
                            }
                            Timber.i("Car to update: result.data[event.position] ${result.data.size}")
                            val action =
                                ViewCarDetailsFragmentDirections.actionViewCarDetailsFragmentToUpdateCarMileage(
                                    myArg = result.data[event.position]
                                )
                            navController.navigate(action)
                        }
                    }

                }
            }

            is ViewCarDetailsEvent.OnDeleteCar -> getCachedUserCars(event)
        }
    }

    private fun getCachedUserCars(
        event: ViewCarDetailsEvent.OnDeleteCar
    ) {
        viewModelScope.launch(ioDispatcher) {
            when (val result = cacheManagerRepository.getUserCarList()) {
                is Ressource.Error -> {
                    uiUtil.displayToastSuspend("Erreur lors de la récupération des voitures")
                }

                is Ressource.Success -> {
                    if (result.data.isNullOrEmpty()) {
                        uiUtil.displayToastSuspend("Vous n'avez encore aucune voiture")
                        return@launch
                    } else {
                        deleteCarRoomUseCase.invoke(result.data[event.position])
                    }
                }

                else -> {}
            }
        }
    }

    fun setNavController(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun updateListCar(cars: List<CarLocal>) {
        _uiState.update {
            ViewCarDetailsState.ViewCarDetailsStateDetailsUIState(
                carLocals = cars
            )
        }
    }
}

sealed interface ViewCarDetailsEvent {
    object OnClickAddCarButton : ViewCarDetailsEvent
    data class OnUpdateMileage(val position: Int) : ViewCarDetailsEvent
    data class OnDeleteCar(val position: Int) : ViewCarDetailsEvent
}