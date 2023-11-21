package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.manageyourcar.UIlayer.composeView.UIState.AddVehiculeMaintenanceUiState
import com.example.manageyourcar.UIlayer.composeView.common.OutlinedSpinner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVehiculeMaintenanceView(
    addVehiculeMaintenanceUiState: AddVehiculeMaintenanceUiState,
    onEvent: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        OutlinedSpinner(
            listMaintenanceName = listOf("Vidange", "Pneu"),
            textLabel = "Opérations"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddVehiculeMaintenaceView() {
    AddVehiculeMaintenanceView(AddVehiculeMaintenanceUiState())
}