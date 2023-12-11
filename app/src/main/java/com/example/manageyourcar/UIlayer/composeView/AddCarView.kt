package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.UIlayer.composeView.UIState.AddCarUIState
import com.example.manageyourcar.UIlayer.composeView.common.CustomDialog
import com.example.manageyourcar.UIlayer.composeView.common.CustomPlaqueImmat
import com.example.manageyourcar.UIlayer.composeView.common.CustomTextField
import com.example.manageyourcar.UIlayer.viewmodel.onCarRequest

@Composable
fun AddCarView(
    uiState: AddCarUIState,
    onEvent: (onCarRequest) -> Unit = {}
) {
    if (uiState.onInternetLost) {
        CustomDialog(title = "Internet perdu")
    } else {
    var openDialog by remember { mutableStateOf(false) }
    if (openDialog) {
        CustomDialog(
            title = "Numéro VIN",
            content = "Le numéro VIN est un identifiant unique de la voiture, il peut généralement être trouvé dans la baie moteur ou sur la carte grise du véhicule"
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Merci d'entrer votre plaque d'immatriculation")
        CustomPlaqueImmat(
            registration = uiState.inputImmat,
            onImmatEvent = { onEvent(onCarRequest.onImmatChanged(it ?: "")) }
        )
        Text(
            textAlign = TextAlign.Center, text = "OU", fontSize = 30.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(0.9f), horizontalArrangement = Arrangement.Center
        ) {
            CustomTextField(
                onValueChange = { onEvent(onCarRequest.onVINChanged(it)) },
                textFieldValue = uiState.inputVIN ?: "",
                label = "Rechercher par numéro VIN",
                readOnly = false,
                keyboardType = KeyboardType.Text,
                labelTextStyle = TextStyle(
                    color = Color.Black,
                )

            )
        }

        Button(onClick = { onEvent(onCarRequest.onClickButton) }) {
            Text(
                text = "Rechercher", fontSize = 20.sp
            )
        }
    }
}}

@Preview(showBackground = true)
@Composable
fun PreviewAddCarView() {
    AddCarView(AddCarUIState())
}