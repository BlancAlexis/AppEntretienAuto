package com.example.manageyourcar.UIlayer.composeView.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPlaqueImmat(
    registration: String?,
    onImmatEvent: (String?) -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(Color.Blue),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        border = BorderStroke(2.dp, Color.Black),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {

            Column(
                modifier = Modifier.wrapContentWidth(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.eu),
                    tint = Color.Yellow,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 7.dp, start = 7.dp, end = 7.dp)
                        .size(24.dp)
                )
                Text(
                    text = "F",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            TextField(
                value = registration ?: "",
                onValueChange = { onImmatEvent(it) },
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSize = 24.sp,
                    letterSpacing = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomPlaqueImmat() {
    CustomPlaqueImmat(
        registration = "AA",
        onImmatEvent = {}
    )
}