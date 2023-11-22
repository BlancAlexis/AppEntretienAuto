package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.manageyourcar.UIlayer.composeView.UIState.AddVehiculeMaintenanceUiState
import com.example.manageyourcar.UIlayer.composeView.common.CustomDialog
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
        if (addVehiculeMaintenanceUiState.onInternetLost) {
            CustomDialog(title = "Internet perdu")
        } else {


            OutlinedSpinner(
                listMaintenanceName = listOf("Vidange", "Pneu"),
                textLabel = "Opérations"
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewAddVehiculeMaintenaceView() {
    AddVehiculeMaintenanceView(AddVehiculeMaintenanceUiState())
}