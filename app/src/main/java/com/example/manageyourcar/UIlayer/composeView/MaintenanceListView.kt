package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.manageyourcar.UIlayer.composeView.UIState.MaintenanceListUiState
import com.example.manageyourcar.UIlayer.viewmodel.onMaintenanceListEvent

@Composable
fun ServicingView(
    uiState: MaintenanceListUiState,
    onEvent: (onMaintenanceListEvent) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (uiState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else {

        Button(onClick = { onEvent(onMaintenanceListEvent.onButtonAddMaintenancePush) }) {
            Text(text = "Ajout d'un acte d'entretien")
        }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Blue),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "")

                }

  /*              DropdownMenu(expanded = true, onDismissRequest = { *//*TODO*//* }) {
                      DropdownMenuItem(onClick = { *//*TODO*//* },text = {Text("Item 1")} )
                       DropdownMenuItem(onClick = { *//*TODO*//* },text = {Text("Item 2")} )
                       }
*/

            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(uiState.listUiState.size) { item ->
                     MaintenanceViewItem(uiState.listUiState[item])
                }
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewServicing() {
    ServicingView(
        uiState = MaintenanceListUiState(listUiState = listOf(), isLoading = true) ,
        onEvent = {})
}
