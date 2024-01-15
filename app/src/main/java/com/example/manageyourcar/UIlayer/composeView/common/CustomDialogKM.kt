package com.example.manageyourcar.UIlayer.composeView.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    UpdateMileageCarDetailsUIState: UpdateMileage,
    onEvent: (UpdateCatEvent) -> Unit = {}
) {
    Column (modifier = Modifier.fillMaxSize(0.4f), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Le kilométrage du véhicule est-il à jour?", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp))

            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = UpdateMileageCarDetailsUIState.newMileage ?: "",
                onValueChange = { onEvent(UpdateCatEvent.newMileage(it)) },
                label = { Text(text = "Kilométrage") },
                placeholder = { Text(text = UpdateMileageCarDetailsUIState.car?.mileage?.last().toString()) },
                modifier = Modifier.padding(10.dp)
            )
        }
        Button(onClick = { onEvent(UpdateCatEvent.OnUpdateMileage)}) {
            Text(text = "Valider")

        }

}
@Preview(showBackground = true)
@Composable
fun previewCustomDialogKM() {
    //CustomDialogKM(UpdateMileageCarDetailsUIState)
}