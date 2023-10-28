package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.manageyourcar.UIlayer.composeView.UIState.ServicingUIState
import com.example.manageyourcar.UIlayer.viewmodel.onServicingEvent

@Composable
fun ServicingView(
    uiState: List<ServicingUIState>,
    onEvent: (onServicingEvent) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Vos prochain entretiens")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Ajouter un entretien")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Blue),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "")

            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(uiState.size) { item ->
               // SerivcingViewItem(item)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewServicing() {
    ServicingView(
        uiState = listOf(ServicingUIState()) ,
        onEvent = {})
}
