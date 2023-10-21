package com.example.manageyourcar.composeView

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.R
import com.example.manageyourcar.composeView.UIState.addCarUIState
import com.example.manageyourcar.composeView.common.CustomDialog
import com.example.manageyourcar.composeView.common.CustomTextField

@Composable
fun AddCarView(
    addCarUIState: addCarUIState
) {
    var openDialog by remember { mutableStateOf(false) }
    if (openDialog){
        CustomDialog(
            title = "Numéro VIN",
            content = "Le numéro VIN est un identifiant unique de la voiture, il peut généralement être trouvé dans la baie moteur ou sur la carte grise du véhicule"
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "Connexion",
                fontSize = 30.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        addCarUIState.inputVIN?.let {
            CustomTextField(
                textFieldValue = it,
                label = "VIN",
                readOnly = false,
                onValueChange = {},
                keyboardType = KeyboardType.Text,
                labelTextStyle = TextStyle(
                    color = Color.Red,
                )

            )
        }

        Button(onClick = { }) {
            Text(
                text = "Rechercher",
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddCarView() {
    AddCarView(addCarUIState())
}