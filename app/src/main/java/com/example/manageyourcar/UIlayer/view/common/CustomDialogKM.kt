package com.example.manageyourcar.UIlayer.view.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.UIlayer.composeView.UIState.UpdateMileage
import com.example.manageyourcar.UIlayer.viewmodel.UpdateCatEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialogKM(
    uiState: UpdateMileage,
    onEvent: (UpdateCatEvent) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = "Le kilométrage de votre ${uiState.car?.model ?: "véhicule"} est-il à jour?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )

        TextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = uiState.newMileage ?: "",
            onValueChange = { onEvent(UpdateCatEvent.newMileage(it)) },
            label = { Text(text = "Kilométrage") },
            placeholder = { Text(text = uiState.car?.mileage?.last().toString()) },
            modifier = Modifier.padding(10.dp)
        )

        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = { onEvent(UpdateCatEvent.OnUpdateMileage) }) {
            Text(text = "Valider")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewCustomDialogKM() {
    //CustomDialogKM(UpdateMileageCarDetailsUIState)
}