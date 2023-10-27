package com.example.manageyourcar.composeView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.manageyourcar.UIlayer.viewmodel.onCarRequest
import com.example.manageyourcar.composeView.UIState.AddCarUIState
import com.example.manageyourcar.composeView.common.CustomDialog
import com.example.manageyourcar.composeView.common.CustomPlaqueImmat
import com.example.manageyourcar.composeView.common.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCarView(
    uiState: AddCarUIState,
    onEvent : (onCarRequest) -> Unit = {}

) {
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
        CustomPlaqueImmat()
        Text(
                textAlign = TextAlign.Center, text = "OU", fontSize = 30.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(0.9f), horizontalArrangement = Arrangement.Center
        ) {
        CustomTextField(
            onValueChange = { println("f") },
            textFieldValue = "it",
            label = "Rechercher par numéro VIN",
            readOnly = false,
            keyboardType = KeyboardType.Text,
            labelTextStyle = TextStyle(
                color = Color.Black,
            )

        )
    }

    Button(onClick = {}) {
        Text(
            text = "Rechercher", fontSize = 20.sp
        )
    }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddCarView() {
    AddCarView(AddCarUIState())
}