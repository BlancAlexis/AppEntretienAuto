package com.example.manageyourcar.UIlayer.viewmodel

import android.app.AlertDialog
import android.location.Location
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.AppApplication
import com.example.manageyourcar.UIlayer.UIState.ViewCarDetailsState
import com.example.manageyourcar.UIlayer.view.activities.MainActivity
import com.example.manageyourcar.UIlayer.view.fragments.ViewCarDetails.ViewCarDetailsFragmentDirections
import com.example.manageyourcar.dataLayer.CacheManagerRepositoryImpl
import com.example.manageyourcar.dataLayer.GlobalEvent
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
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext

class ViewCarDetailsViewModel constructor( val cacheManagerRepository: CacheManagerRepository): ViewModel(), KoinComponent,GlobalEvent {
    private val getUserCarsUseCase by inject<GetUserCarsUseCase>()
    private val deleteCarRoomUseCase by inject<DeleteCarRoomUseCase>()
    private val _uiState = MutableStateFlow<ViewCarDetailsState>(ViewCarDetailsState.Loading)
    val uiState = _uiState.asStateFlow()
    private lateinit var navController: NavController
    val a : MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserCarsUseCase.invoke().collect { result ->
                when (result) {
                    is Ressource.Error -> Log.e("ViewCarDetailsViewModel", "Utilisateur inconnu")
                    is Ressource.Loading -> TODO()
                    is Ressource.Success -> result.data?.let {
                        updateListCar(it)
                        cacheManagerRepository.saveUserCarList(it)
                        cacheManagerRepository.getUserCarList()
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
                if(event.position == 0){
                    Toast.makeText(AppApplication.instance.applicationContext, "Vous n'avez encore aucune voiture", Toast.LENGTH_SHORT).show()
                    return
                }
                val action = ViewCarDetailsFragmentDirections.actionViewCarDetailsFragmentToUpdateCarMileage(
                        myArg = _uiState.value?.let {
                            it.let {
                                it as ViewCarDetailsState.ViewCarDetailsStateDetailsUIState
                            }.carLocals.getOrNull(event.position)
                        })
                    navController.navigate(action)
            }

            is ViewCarDetailsEvent.OnDeleteCar ->
                viewModelScope.launch(Dispatchers.IO) {
                    deleteCarRoomUseCase.deleteCar(event.idCar)
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

    override fun onInternetConnectionLost() {
        println("conNNNNnNnnNnN")
    }

    override fun onInternetConnectionAvailable() {
        println("conNNNNnNnnNnN")
    }

    override fun onLocationChanged(location: Location) {
        println("conNNNNnNnnNnN")
    }


}

sealed interface ViewCarDetailsEvent {
    object OnClickAddCarButton : ViewCarDetailsEvent
    data class OnUpdateMileage(val position : Int) : ViewCarDetailsEvent
    data class OnDeleteCar(val idCar: CarLocal) : ViewCarDetailsEvent
}