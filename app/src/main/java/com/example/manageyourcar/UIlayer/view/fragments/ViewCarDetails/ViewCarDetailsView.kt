package com.example.manageyourcar.UIlayer.view.fragments.ViewCarDetails

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.UIState.ViewCarDetailsState
import com.example.manageyourcar.UIlayer.view.common.InformationRow
import com.example.manageyourcar.UIlayer.viewmodel.ViewCarDetailsEvent
import com.example.manageyourcar.dataLayer.model.Car
import java.util.Calendar


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewCarDetailsView(
    uiState: ViewCarDetailsState,
    onEvent: (ViewCarDetailsEvent) -> Unit = {}
) {
    val juraFamily = FontFamily(
        Font(R.font.jura, FontWeight.Medium)
    )
    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {

        AnimatedVisibility(visible = uiState is ViewCarDetailsState.Loading) {
            CircularProgressIndicator(color = colorResource(id = R.color.black))
        }


        AnimatedVisibility(visible = uiState is ViewCarDetailsState.ViewCarDetailsStateDetailsUIState) {
            uiState as ViewCarDetailsState.ViewCarDetailsStateDetailsUIState
            val pagerState = rememberPagerState(pageCount = { uiState.cars.size })
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                verticalArrangement = Arrangement.Top,

                ) {
                Text(
                    text = "Vos Véhicules",
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = juraFamily,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = { onEvent(ViewCarDetailsEvent.OnUpdateMileage(pagerState.currentPage)) }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_auto_graph_24),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    Button(onClick = {
                     //   onEvent(ViewCarDetailsEvent.OnDeleteCar(uiState.cars[pagerState.currentPage]))
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    TextButton(
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        onClick = {
                            onEvent(ViewCarDetailsEvent.OnClickAddCarButton)
                        },
                        shape = RoundedCornerShape(30.dp),
                    ) {
                        Text(
                            text = "Ajouter une voiture",
                            textAlign = TextAlign.Center,
                            fontFamily = juraFamily,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }

                LaunchedEffect(pagerState) {
                    snapshotFlow { pagerState.currentPage }.collect { page ->
                        Log.d("Page change", "Page changed to $page")
                    }
                }

                HorizontalPager(state = pagerState) {
                    val realseDate = Calendar.getInstance()
                    realseDate.time = uiState.cars[pagerState.currentPage].releaseDate

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            //.background(Color(209, 228, 255))
                            .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Image(
                            contentScale = ContentScale.Crop,
                            painter = painterResource(id = R.drawable.citroen),
                            contentDescription = "",
                            modifier = Modifier
                                .clip(
                                    shape = RoundedCornerShape(
                                        topStart = 20.dp,
                                        topEnd = 20.dp
                                    )
                                )
                                .fillMaxWidth()
                                .height(138.dp),
                        )
                        Box(
                            modifier = Modifier.padding(0.dp)
                        ) {
                            Column(
                                Modifier
                                    .background(
                                        MaterialTheme.colorScheme.primaryContainer,
                                        shape = RoundedCornerShape(
                                            bottomStart = 20.dp,
                                            bottomEnd = 20.dp
                                        )
                                    )
                                    .fillMaxWidth(),
                                horizontalAlignment = CenterHorizontally,
                            ) {
                                Text(
                                    uiState.cars[pagerState.currentPage].brand + " " + uiState.cars[pagerState.currentPage].model,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 5.dp, bottom = 10.dp),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    textAlign = TextAlign.Center,
                                    fontFamily = juraFamily,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                InformationRow(
                                    title1 = "Parution",
                                    content1 = realseDate.get(Calendar.YEAR).toString(),
                                    icon1 = painterResource(R.drawable.outline_calendar_month_24),
                                    title2 = "Carburant",
                                    content2 = uiState.cars[pagerState.currentPage].fuel,
                                    icon2 = painterResource(R.drawable.baseline_oil_barrel_24)
                                )
                                InformationRow(
                                    title1 = "Transmission",
                                    content1 = uiState.cars[pagerState.currentPage].transmission,
                                    icon1 = painterResource(R.drawable.auto_transmission),
                                    title2 = "Motorisation",
                                    content2 = uiState.cars[pagerState.currentPage].motorization,
                                    icon2 = painterResource(R.drawable.baseline_directions_car_24),
                                    modifier = Modifier.padding(top = 10.dp),
                                )
                                InformationRow(
                                    title1 = "Puissance",
                                    content1 = uiState.cars[pagerState.currentPage].power.toString() + " ch",
                                    icon1 = painterResource(R.drawable.baseline_bolt_24),
                                    title2 = "Couple",
                                    content2 = uiState.cars[pagerState.currentPage].torque.toString() + " nm",
                                    icon2 = painterResource(R.drawable.baseline_fast_forward_24),
                                    modifier = Modifier.padding(top = 10.dp),
                                )
                                InformationRow(
                                    title1 = "Vitesse Max.",
                                    content1 = uiState.cars[pagerState.currentPage].maxSpeed.toString() + " km/h",
                                    icon1 = painterResource(R.drawable.outline_speed_24),
                                    title2 = "Kilométrage",
                                    content2 = uiState.cars[pagerState.currentPage].mileage.last()
                                        .toString() + " km",
                                    icon2 = painterResource(R.drawable.baseline_auto_graph_24),
                                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                                )
                                Row(
                                    Modifier
                                        .padding(top = 5.dp, bottom = 5.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {

                                    IconButton(onClick = {}) {
                                        if (pagerState.currentPage != 0) {
                                            Icon(
                                                painter = painterResource(R.drawable.baseline_arrow_back_24),
                                                contentDescription = "",
                                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                                modifier = Modifier.size(36.dp)
                                            )
                                        }
                                    }
                                    if (pagerState.currentPage != uiState.cars.size - 1) {
                                        IconButton(onClick = { /* do something */ }) {
                                            Icon(
                                                painter = painterResource(R.drawable.baseline_arrow_forward_24),
                                                contentDescription = "",
                                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                                modifier = Modifier.size(36.dp)
                                            )
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewViewCarDetailsView() {
    var carstest = listOf<Car>()
    carstest = carstest + Car(
        brand = "Citroën",
        model = "C4 VTS",
        fuel = "Essence",
        maxSpeed = 200,
        mileage = arrayListOf(200547),
        motorization = "1.6 HDI FAP",
        power = 180,
        releaseDate = Calendar.getInstance().time,
        torque = 280,
        transmission = "Manuelle",
    )

    carstest = carstest + Car(
        brand = "Citroën",
        model = "C3 VTS",
        fuel = "Essence",
        maxSpeed = 200,
        mileage = arrayListOf(200547),
        motorization = "1.6 HDI FAP",
        power = 180,
        releaseDate = Calendar.getInstance().time,
        torque = 280,
        transmission = "Manuelle",
    )

    ViewCarDetailsView(onEvent = {}, uiState =  ViewCarDetailsState.ViewCarDetailsStateDetailsUIState(carstest));
}