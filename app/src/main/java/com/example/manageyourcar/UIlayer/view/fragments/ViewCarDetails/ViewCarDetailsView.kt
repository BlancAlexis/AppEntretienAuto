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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.UIState.ViewCarDetailsState
import com.example.manageyourcar.UIlayer.view.common.InformationRow
import com.example.manageyourcar.UIlayer.viewmodel.ViewCarDetailsEvent
import com.example.manageyourcar.dataLayer.model.Car
import kotlin.math.absoluteValue


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
                    text = stringResource(R.string.your_cars),
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = juraFamily,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Row(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
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
                        onEvent(ViewCarDetailsEvent.OnDeleteCar(pagerState.currentPage))
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
                            text = stringResource(R.string.add_car),
                            textAlign = TextAlign.Center,
                            fontFamily = juraFamily,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }
                if (uiState.cars.isEmpty()) {
                    Text(
                        text = stringResource(R.string.no_car),
                        modifier = Modifier
                            .padding(vertical = 15.dp, horizontal = 15.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = juraFamily,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                } else {
                    LaunchedEffect(pagerState) {
                        snapshotFlow { pagerState.currentPage }.collect { page ->
                            Log.d("Page change", "Page changed to $page")
                        }
                    }

                    HorizontalPager(state = pagerState) { page ->
                        Card(
                            colors = CardDefaults.cardColors(Color.Transparent),
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
                        ) {

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
                                            title1 = stringResource(R.string.parution),
                                            content1 = uiState.cars[pagerState.currentPage].releaseDate,
                                            icon1 = painterResource(R.drawable.outline_calendar_month_24),
                                            title2 = stringResource(R.string.fuel),
                                            content2 = uiState.cars[pagerState.currentPage].fuel,
                                            icon2 = painterResource(R.drawable.baseline_oil_barrel_24)
                                        )
                                        InformationRow(
                                            title1 = stringResource(R.string.transmission),
                                            content1 = uiState.cars[pagerState.currentPage].transmission,
                                            icon1 = painterResource(R.drawable.auto_transmission),
                                            title2 = stringResource(R.string.engine),
                                            content2 = uiState.cars[pagerState.currentPage].motorization,
                                            icon2 = painterResource(R.drawable.baseline_directions_car_24),
                                            modifier = Modifier.padding(top = 10.dp),
                                        )
                                        InformationRow(
                                            title1 = stringResource(R.string.horsepower),
                                            content1 = uiState.cars[pagerState.currentPage].power.toString() + " ch",
                                            icon1 = painterResource(R.drawable.baseline_bolt_24),
                                            title2 = stringResource(R.string.torque),
                                            content2 = uiState.cars[pagerState.currentPage].torque.toString() + " nm",
                                            icon2 = painterResource(R.drawable.baseline_fast_forward_24),
                                            modifier = Modifier.padding(top = 10.dp),
                                        )
                                        InformationRow(
                                            title1 = stringResource(R.string.max_speed),
                                            content1 = uiState.cars[pagerState.currentPage].maxSpeed.toString() + " km/h",
                                            icon1 = painterResource(R.drawable.outline_speed_24),
                                            title2 = stringResource(R.string.mileage),
                                            content2 = uiState.cars[pagerState.currentPage].mileage.last()
                                                .toString() + " km",
                                            icon2 = painterResource(R.drawable.baseline_auto_graph_24),
                                            modifier = Modifier.padding(
                                                top = 10.dp,
                                                bottom = 10.dp
                                            ),
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
        releaseDate = "2005-01-01",
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
        releaseDate = "2005-01-01",
        torque = 280,
        transmission = "Manuelle",
    )

    ViewCarDetailsView(
        onEvent = {},
        uiState = ViewCarDetailsState.ViewCarDetailsStateDetailsUIState(carstest)
    )
}