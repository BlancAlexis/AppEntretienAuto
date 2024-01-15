package com.example.manageyourcar.UIlayer.composeView.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.UIlayer.composeView.UIState.UpdateMileage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialogKM(UpdateMileageCarDetailsUIState: UpdateMileage) {
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Le kilométrage de vos véhicules sont-ils à jour?", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp))
            LazyRow() {
                items(UpdateMileageCarDetailsUIState.ListCar.size) { index ->
                    Text(text = UpdateMileageCarDetailsUIState.ListCar[index].model, modifier = Modifier.padding(10.dp))
                }
            }
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Kilométrage") },
                placeholder = { Text(text = "23") },
                modifier = Modifier.padding(10.dp)
            )
        Button(onClick = {}) {
            Text(text = "Valider")

        }

        }
}
@Preview(showBackground = true)
@Composable
fun previewCustomDialogKM() {
    //CustomDialogKM(UpdateMileageCarDetailsUIState)
}