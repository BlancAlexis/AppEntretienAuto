package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.composeView.UIState.SignInUiState
import com.example.manageyourcar.UIlayer.composeView.UIState.ViewCarDetailsUIState
import com.example.manageyourcar.UIlayer.composeView.common.CustomDialog
import com.example.manageyourcar.UIlayer.composeView.common.CustomTextField
import com.example.manageyourcar.UIlayer.composeView.common.InformationBox
import com.example.manageyourcar.UIlayer.composeView.common.InformationRow
import com.example.manageyourcar.UIlayer.viewmodel.UserSubscriptionEvent
import java.text.DateFormat
import java.time.Instant
import java.time.ZoneOffset.UTC
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

@Composable
fun ViewCarDetailsView(
    uiState: ViewCarDetailsUIState,
) {
    val juraFamily = FontFamily(
        Font(R.font.jura, FontWeight.Medium)
    )
    val realseDate = Calendar.getInstance();
    realseDate.setTime(uiState.releaseDate);

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color(0, 97, 162))

    ) {
        Text(
            text = "Vos Véhicules",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            textAlign = TextAlign.Center,
            fontFamily = juraFamily,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        TextButton(
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(209, 228, 255)),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 20.dp)
                .height(43.dp),
        ) {
            Text(
                text = "Ajouter une voiture",
                textAlign = TextAlign.Center,
                fontFamily = juraFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0, 29, 54),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                //.background(Color(209, 228, 255))
                .padding(start = 20.dp, end = 20.dp, top = 15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
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
                    .height(135.dp),
            )
            Box(
                modifier = Modifier.padding(0.dp)
            ) {
                Column(
                    Modifier
                        .background(
                            Color(209, 228, 255),
                            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                        )
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        uiState.brand + " " + uiState.model,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp, bottom = 30.dp),
                        color = Color(0, 29, 54),
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
                        content2 = uiState.fuel ?: "N/A",
                        icon2 = painterResource(R.drawable.baseline_oil_barrel_24)
                    )
                    InformationRow(
                        title1 = "Transmission",
                        content1 = uiState.transmission ?: "N/A",
                        icon1 = painterResource(R.drawable.auto_transmission),
                        title2 = "Motorisation",
                        content2 = uiState.motorization ?: "N/A",
                        icon2 = painterResource(R.drawable.baseline_directions_car_24),
                        modifier = Modifier.padding(top = 10.dp),
                    )
                    InformationRow(
                        title1 = "Puissance",
                        content1 = uiState.power.toString() + "ch",
                        icon1 = painterResource(R.drawable.baseline_bolt_24),
                        title2 = "Couple",
                        content2 = uiState.torque.toString() + "nm",
                        icon2 = painterResource(R.drawable.baseline_fast_forward_24),
                        modifier = Modifier.padding(top = 10.dp),
                    )
                    InformationRow(
                        title1 = "Vitesse Max.",
                        content1 = uiState.maxSpeed.toString() + "km/h",
                        icon1 = painterResource(R.drawable.outline_speed_24),
                        title2 = "Kilométrage",
                        content2 = uiState.mileage.toString() + "km",
                        icon2 = painterResource(R.drawable.baseline_auto_graph_24),
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    )
                    Row(
                        Modifier.padding(top = 30.dp, bottom = 30.dp),
                    ) {
                        Icon(
                            modifier = Modifier.size(36.dp),
                            painter = painterResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = "",
                            tint = Color(0, 29, 54),
                        )
                        Box(
                            Modifier.width(100.dp)
                        ) {
                        }
                        Icon(
                            modifier = Modifier.size(36.dp),
                            painter = painterResource(R.drawable.baseline_arrow_forward_24),
                            contentDescription = "",
                            tint = Color(0, 29, 54),
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewViewCarDetailsView() {
    ViewCarDetailsView(
        uiState = ViewCarDetailsUIState(
            brand = "Citroën",
            model = "C4 VTS",
            fuel = "Essence",
            maxSpeed = 200,
            mileage = 200547,
            motorization = "1.6 HDI FAP",
            power = 180,
            releaseDate = Calendar.getInstance().getTime(),
            torque = 280,
            transmission = "Manuelle"
        )
    )
}