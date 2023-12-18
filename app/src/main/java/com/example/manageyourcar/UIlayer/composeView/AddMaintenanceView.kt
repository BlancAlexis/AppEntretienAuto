package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
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
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddMaintenanceView(
    uiState: AddVehiculeMaintenanceUiState,
    onEvent: (onMaintenanceEvent) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        var checked by remember { mutableStateOf(false) }
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
                        checked = false
                    }
                )
            }
        Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val pagerState = rememberPagerState(pageCount = {uiState.listMaintenance.size})
                LaunchedEffect(pagerState) {
                    snapshotFlow { pagerState.currentPage }.collect { page ->
                        onMaintenanceEvent.onMaintenanceChanged(uiState.listMaintenance[page])
                    }
                }

                Text(text = "Ajouter une opération", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                HorizontalPager(state = pagerState) { page ->
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .graphicsLayer {
                                    val pageOffset = (
                                            (pagerState.currentPage - page) + pagerState
                                                .currentPageOffsetFraction
                                            ).absoluteValue

                                    alpha = lerp(
                                        start = 0.5f,
                                        stop = 1f,
                                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                    )
                                }
                            ,
                        ) {
                            Image(
                                modifier = Modifier.padding(start = 15.dp),
                                painter = painterResource(id = uiState.listMaintenance[page].image),
                                contentDescription = ""
                            )
                            Text(text = uiState.listMaintenance[page].name, fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(start = 25.dp, top = 200.dp))
                        }
                    }
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(bottom = 20.dp, top = 5.dp, start = 3.dp, end = 3.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(6.dp)
                    )
                }
            }
                OutlinedSpinner(
                    listMaintenanceName = uiState.listCars.map { it.model },
                    textLabel = "Votre véhicule",
                    onItemSelect = { nomCar ->
                        uiState.listCars.find { it.model == nomCar   }
                            ?.let { it1 -> onEvent(onMaintenanceEvent.onCarChanged(it1)) }
                    })

                CustomTextField(
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        onEvent(onMaintenanceEvent.onPriceChanged(it.toInt()))
                    },
                    textFieldValue = uiState.price.toString(),
                    label = "Prix",
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(bottom = 20.dp)
                )
                CustomTextField(
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        onEvent(onMaintenanceEvent.onMileageChanged(it.toInt()))
                    },
                    textFieldValue = uiState.mileage.toString(),
                    label = "Kilométrage",
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
                Row {
                    Checkbox(checked = checked, onCheckedChange = { isChecked ->
                        checked = isChecked
                    if(checked){
                        selectedDate.value = Date.from(Instant.now())
                    }})
                    IconButton(onClick = {
                        showCalendar.value = true
                    }) {
                        Icon(imageVector = Icons.Outlined.DateRange, contentDescription = "")
                    }
                }
                Text(text = "Date : ${selectedDate.value}")
                Button(colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primaryColor)), onClick = {
                    onEvent(onMaintenanceEvent.onValidatePressed)
                }) {
                    Text(text = "Ajouter")
                }
            }
        }}
    }

@Preview(showBackground = true)
@Composable
fun PreviewAddMaintenaceView() {
    AddMaintenanceView(AddVehiculeMaintenanceUiState())
}