package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.UIlayer.composeView.UIState.AddVehiculeMaintenanceUiState
import com.example.manageyourcar.UIlayer.composeView.common.CalendarView
import com.example.manageyourcar.UIlayer.composeView.common.CustomTextField
import com.example.manageyourcar.UIlayer.composeView.common.OutlinedSpinner
import com.example.manageyourcar.UIlayer.view.fragments.onMaintenanceEvent

@Composable
fun AddMaintenanceView(
    uiState: AddVehiculeMaintenanceUiState,
    onEvent: (onMaintenanceEvent) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val showCalendar = remember { mutableStateOf(false) }
        val selectedDate = remember { mutableStateOf("") }

        if (showCalendar.value) {
            CalendarView(
                onDateSelected = { date ->
                    selectedDate.value = date
                    onMaintenanceEvent.onDateChanged(date)
                    showCalendar.value = false
                }
            )
        }

        Text(text = "Ajouter une opération", fontSize = 18.sp)
        OutlinedSpinner(
            listMaintenanceName = listOf("C4 VTS", "Corsa D GSI", "Bugatti chiron"),
            textLabel = "votre véhicule",
            onItemSelect = { car ->
                when (car) {
                     is onMaintenanceEvent.onCarChanged -> onMaintenanceEvent.onCarChanged(car)
                    else -> throw Exception("Unexpected item type")
                }
            })
        OutlinedSpinner(
            listMaintenanceName = listOf("Vidange", "Pneu", "Frein"),
            textLabel = "opération effectué",
            onItemSelect = { item ->
                when (item) {
                       is onMaintenanceEvent.onMaintenanceChanged -> onMaintenanceEvent.onMaintenanceChanged(item)
                    else -> throw Exception("Unexpected item type")
                }
            })
        CustomTextField(textFieldValue = "e", label = "Prix")
        IconButton(onClick = {
            showCalendar.value = true
        }) {
            Icon(imageVector = Icons.Outlined.Info, contentDescription = "")
        }
        Button(onClick = {
            onEvent(onMaintenanceEvent.onValidatePressed)
        }) {
            Text(text = "Ajouter")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddMaintenaceView() {
    AddMaintenanceView(AddVehiculeMaintenanceUiState())
}