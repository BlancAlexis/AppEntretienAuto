package com.example.manageyourcar.UIlayer.view.fragments.OBD

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OBDView() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Canvas(modifier = Modifier.fillMaxWidth(0.6f)) {
                drawCircle(color = Color.Red, radius = 100f)
            }
            Canvas(modifier = Modifier.fillMaxWidth(0.6f)) {
                drawCircle(color = Color.Red, radius = 100f)
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Canvas(modifier = Modifier.fillMaxWidth(0.6f)) {
                drawCircle(color = Color.Red, radius = 100f)
            }
            Canvas(modifier = Modifier.fillMaxWidth(0.6f)) {
                drawCircle(color = Color.Red, radius = 100f)
            }
        }
        Row(horizontalArrangement = Arrangement.End) {


            Column(
                modifier = Modifier.fillMaxWidth(0.4f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(1f)) {
                    Text(text = "Recherche DTC")

                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(1f)) {
                    Text(text = "Aide")

                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(1f)) {
                    Text(text = "Déconnexion")

                }
                Text(
                    text = "Opel corsa 85",
                    modifier = Modifier.fillMaxWidth(1f),
                    color = Color.Gray
                )
                Text(
                    text = "25656565656321",
                    modifier = Modifier.fillMaxWidth(1f),
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OBDViewPreview() {
    OBDView()
}