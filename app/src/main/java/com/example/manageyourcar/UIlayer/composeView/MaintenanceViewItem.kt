package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.manageyourcar.UIlayer.composeView.UIState.ServicingUIState

@Composable
fun MaintenanceViewItem(
    uiState: ServicingUIState
) {
    var linearProgressColor by remember {
        mutableStateOf(Color.Green)
    }
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(15.dp),
        shape = RoundedCornerShape(7.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
            ) {
                uiState.carName?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(6.dp, 6.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    uiState.description?.let {
                        Text(
                            text = it,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth(0.7f)
                        )
                    }
                    uiState.mileage?.let { Text(text = it) }
                }

                uiState.progressIndicator?.let {
                    if (it >= 0.9) {
                        linearProgressColor = Color.Red
                    }
                    if (it >= 0.7 && it < 0.7)
                        linearProgressColor = Color.Yellow

                    LinearProgressIndicator(
                        color = linearProgressColor,
                        trackColor = Color.Gray,
                        progress = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clip(RoundedCornerShape(100))
                            .height(10.dp)
                    )
                }
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun PreviewServicingItem() {
    MaintenanceViewItem(
        ServicingUIState(
            "Citroen C4 VTS 1.6 VTS 1.6 HDI 110CH",
            "123333km",
            0.91f,
            "Changement plaquette de frein moteur"
        )
    )
}
