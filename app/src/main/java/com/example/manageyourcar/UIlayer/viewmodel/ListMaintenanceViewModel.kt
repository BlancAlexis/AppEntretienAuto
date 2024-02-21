package com.example.manageyourcar.UIlayer.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.UIState.MaintenanceListUiState
import com.example.manageyourcar.UIlayer.UIState.ServicingUIState
import com.example.manageyourcar.UIlayer.UIState.SortType
import com.example.manageyourcar.UIlayer.UIUtil
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.MaintenanceWithCarEntity
import com.example.manageyourcar.dataLayer.model.MaintenanceService
import com.example.manageyourcar.domainLayer.repository.CacheManagerRepository
import com.example.manageyourcar.domainLayer.useCaseBusiness.LogoutUserUseCase
import com.example.manageyourcar.domainLayer.useCaseRoom.servicing.GetAllUserMaintenanceUseCase
import com.example.manageyourcar.domainLayer.utils.MaintenanceActScheddule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ListMaintenanceViewModel(
    private val cacheManagerRepository: CacheManagerRepository, private val uiUtil: UIUtil
) : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(MaintenanceListUiState())
    val uiState = _uiState.asStateFlow()

    private lateinit var navController: NavController
    private val getAllUserMaintenanceUseCase by inject<GetAllUserMaintenanceUseCase>()
    private val logoutUserUseCase by inject<LogoutUserUseCase>()

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO


    init {
        getUserMaintenanceLocalStorage()
    }

    private fun getUserMaintenanceLocalStorage() {
        viewModelScope.launch(ioDispatcher) {
            getAllUserMaintenanceUseCase.invoke().collect { result ->
                when (result) {
                    is Ressource.Error -> uiUtil.displayToastSuspend("erreur dans le chargement des entretiens")
                    is Ressource.Success -> result.data?.let { listLoading(it) }
                    else -> {}
                }
            }
        }
    }


    fun onBackPressed() {
        viewModelScope.launch {
            when (val result =
                logoutUserUseCase.logoutUser()) {
                is Ressource.Error -> uiUtil.displayToastSuspend(
                    result.error?.localizedMessage ?: "erreur"
                )

                is Ressource.Success -> return@launch
                else -> {}
            }
        }
    }

    fun setNavController(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun listLoading(newData: List<MaintenanceWithCarEntity>) {
        _uiState.update {
            it.copy(isLoading = false, listUiState = newData.map { entretien ->
                var progressIndicator = 0.0f
                var description: String = ""
                when (entretien.maintenanceEntity.serviceType) {
                    is MaintenanceService.Freins -> {
                        description = entretien.maintenanceEntity.serviceType.name
                        progressIndicator =
                            ((entretien.maintenanceEntity.mileage.toFloat() / MaintenanceActScheddule.valueOf(
                                entretien.maintenanceEntity.serviceType.name.uppercase()
                            ).km.toFloat()) * 100).toFloat()
                    }

                    is MaintenanceService.Pneus -> {
                        description = entretien.maintenanceEntity.serviceType.name
                        progressIndicator =
                            ((entretien.maintenanceEntity.mileage.toFloat() / MaintenanceActScheddule.valueOf(
                                entretien.maintenanceEntity.serviceType.name.uppercase()
                            ).km.toFloat()) * 100).toFloat()
                    }

                    is MaintenanceService.Vidange -> {
                        description = entretien.maintenanceEntity.serviceType.name
                        progressIndicator =
                            ((entretien.maintenanceEntity.mileage.toFloat() / MaintenanceActScheddule.valueOf(
                                entretien.maintenanceEntity.serviceType.name.uppercase()
                            ).km.toFloat()) * 100).toFloat()
                    }
                }

                ServicingUIState(
                    carName = entretien.carEntity.brand + " " + entretien.carEntity.model,
                    mileage = entretien.maintenanceEntity.mileage.toString(),
                    progressIndicator = progressIndicator.toFloat(),
                    description = description,
                )
            })
        }

    }


    private fun listLoad() {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
    }


    fun onEvent(event: OnMaintenanceListEvent) {
        when (event) {
            is OnMaintenanceListEvent.OnButtonAddMaintenancePush -> {
                when (val result = cacheManagerRepository.getUserCarList()) {
                    is Ressource.Error -> uiUtil.displayToast(
                        result.error?.localizedMessage ?: "erreur"
                    )

                    is Ressource.Success -> {
                        if (result.data.isNullOrEmpty()) {
                            uiUtil.displayToast("Ajouter une voiture d'abord")
                            return
                        }
                        navController.navigate(R.id.addMaintenanceFragment)
                    }

                    else -> {}
                }
            }

            is OnMaintenanceListEvent.OnSortMethodChanged -> changeSortMethod(event)
        }
    }

    private fun changeSortMethod(event: OnMaintenanceListEvent.OnSortMethodChanged) {
        when (event.newMethod) {
            SortType.croissant -> _uiState.update {
                it.copy(listUiState = it.listUiState.sortedBy { it.mileage })
            }

            SortType.décroissant -> _uiState.update {
                it.copy(listUiState = it.listUiState.sortedByDescending { it.mileage })
            }
        }

    }

}

sealed interface OnMaintenanceListEvent {
    object OnButtonAddMaintenancePush : OnMaintenanceListEvent
    class OnSortMethodChanged(val newMethod: SortType) : OnMaintenanceListEvent

}

