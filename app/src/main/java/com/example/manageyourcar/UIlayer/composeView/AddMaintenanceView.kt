package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.composeView.UIState.AddVehiculeMaintenanceUiState
import com.example.manageyourcar.UIlayer.composeView.common.CalendarView
import com.example.manageyourcar.UIlayer.composeView.common.CustomDialog
import com.example.manageyourcar.UIlayer.composeView.common.CustomTextField
import com.example.manageyourcar.UIlayer.composeView.common.OutlinedSpinner
import com.example.manageyourcar.UIlayer.viewmodel.onMaintenanceEvent
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMaintenanceView(
    uiState: AddVehiculeMaintenanceUiState,
    onEvent: (onMaintenanceEvent) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        if (uiState.onInternetLost) {
            CustomDialog(title = "Internet perdu")
        } else {

            val showCalendar = remember { mutableStateOf(false) }
            val selectedDate = remember { mutableStateOf(Date.from(Instant.now())) }

            if (showCalendar.value) {
                CalendarView(
                    onDateSelected = { date ->
                        selectedDate.value = SimpleDateFormat("dd/MM/yyyy").parse(date)
                        onMaintenanceEvent.onDateChanged(date)
                        showCalendar.value = false
                    }
                )
            }
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ajouter une opération", fontSize = 25.sp, fontWeight = FontWeight.Bold)

                LazyRow(contentPadding = PaddingValues(8.dp)) {
                    items(uiState.listMaintenance) { maintenance ->
                        Card(
                            modifier = Modifier
                                .wrapContentSize(),
                        ) {
                            Image(
                                painter = painterResource(id = maintenance.image),
                                contentDescription = ""
                            )
                        }
                    }
                }
                OutlinedSpinner(
                    listMaintenanceName = uiState.listCars.map { it.model} ?: listOf("corsa", "clio", "megane"),
                    textLabel = "Votre véhicule",
                    onItemSelect = { nomCar ->
                        uiState.listCars.find { it.model == nomCar   }
                            ?.let { it1 -> onMaintenanceEvent.onCarChanged(it1) }

                    })

                CustomTextField(
                    onValueChange = {
                        onMaintenanceEvent.onPriceChanged(it.toInt())
                    },
                    textFieldValue = uiState.price.toString(),
                    label = "Prix",
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
                CustomTextField(
                    onValueChange = {
                        onMaintenanceEvent.onMileageChanged(it.toInt())
                    },
                    textFieldValue = uiState.mileage.toString(),
                    label = "Kilométrage",
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
                Row {
                    Checkbox(checked = true, onCheckedChange = { })
                    IconButton(onClick = {
                        showCalendar.value = true
                    }) {
                        Icon(imageVector = Icons.Outlined.DateRange, contentDescription = "")
                    }
                }
                Text(text = "Date : ${selectedDate.value}")
                Button(onClick = {
                    onEvent(onMaintenanceEvent.onValidatePressed)
                }) {
                    Text(text = "Ajouter")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddMaintenaceView() {
    AddMaintenanceView(AddVehiculeMaintenanceUiState())
}