package com.example.manageyourcar.UIlayer.viewmodel

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.UIlayer.UIState.ViewCarDetailsState
import com.example.manageyourcar.UIlayer.view.fragments.ViewCarDetails.ViewCarDetailsFragmentDirections
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.useCaseRoom.car.DeleteCarRoomUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.car.GetUserCarsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ViewCarDetailsViewModel(val cacheManagerRepository: CacheManagerRepository) :
    ViewModel(), KoinComponent {
    private val getUserCarsUseCase by inject<GetUserCarsUseCase>()
    private val deleteCarRoomUseCase by inject<DeleteCarRoomUseCase>()
    private val _uiState = MutableStateFlow<ViewCarDetailsState>(ViewCarDetailsState.Loading)
    val uiState = _uiState.asStateFlow()
    private lateinit var navController: NavController

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserCarsUseCase.invoke().collect { result ->
                when (result) {
                    is Ressource.Error -> Log.e("ViewCarDetailsViewModel", "Utilisateur inconnu")
                    is Ressource.Loading -> TODO()
                    is Ressource.Success -> result.data?.let {
                        updateListCar(it)
                        cacheManagerRepository.saveUserCarList(it)
                    }
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
                when (val result = cacheManagerRepository.getUserCarList()) {
                    is Ressource.Error -> {
                        Toast.makeText(
                            AppApplication.instance.applicationContext,
                            "Erreur lors de la récupération des voitures",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }

                    is Ressource.Loading -> {
                        Toast.makeText(
                            AppApplication.instance.applicationContext,
                            "Chargement des voitures",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }

                    is Ressource.Success -> {
                        if (result.data.isNullOrEmpty()) {
                            Toast.makeText(
                                AppApplication.instance.applicationContext,
                                "Vous n'avez encore aucune voiture",
                                Toast.LENGTH_SHORT
                            ).show()
                            return
                        }
                        val action =
                            ViewCarDetailsFragmentDirections.actionViewCarDetailsFragmentToUpdateCarMileage(
                                myArg = result.data[event.position]
                            )
                        navController.navigate(action)
                    }
                }

            }

            is ViewCarDetailsEvent.OnDeleteCar ->
                viewModelScope.launch(Dispatchers.IO) {
                    when (val result = cacheManagerRepository.getUserCarList()) {
                        is Ressource.Error -> {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    AppApplication.instance.applicationContext,
                                    "Erreur lors de la récupération des voitures",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        is Ressource.Loading -> {
                        }

                        is Ressource.Success -> {
                            if (result.data.isNullOrEmpty()) {
                                withContext(Dispatchers.Main) {

                                    Toast.makeText(
                                        AppApplication.instance.applicationContext,
                                        "Vous n'avez encore aucune voiture",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return@withContext
                                }
                                return@launch
                            } else {
                                deleteCarRoomUseCase.deleteCar(result.data[event.position])
                            }

                        }
                    }
                }
        }


    }

    fun setNavController(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun updateListCar(data: List<CarLocal>) {
        _uiState.update {
            ViewCarDetailsState.ViewCarDetailsStateDetailsUIState(
                carLocals = data
            )
        }
    }

}

sealed interface ViewCarDetailsEvent {
    object OnClickAddCarButton : ViewCarDetailsEvent
    data class OnUpdateMileage(val position: Int) : ViewCarDetailsEvent
    data class OnDeleteCar(val position: Int) : ViewCarDetailsEvent
}