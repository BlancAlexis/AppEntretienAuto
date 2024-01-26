package com.example.manageyourcar.UIlayer.view.fragments.ViewListMaintenance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.composeView.UIState.MaintenanceListUiState
import com.example.manageyourcar.UIlayer.composeView.UIState.SortType
import com.example.manageyourcar.UIlayer.composeView.common.CustomDialog
import com.example.manageyourcar.UIlayer.viewmodel.OnMaintenanceListEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewListMaintenanceView(
    uiState: MaintenanceListUiState,
    onEvent: (OnMaintenanceListEvent) -> Unit = {}
) {
    var openSortChoice by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (uiState.onInternetLost) {
            CustomDialog(title = "Internet perdu")
        } else {
            if (uiState.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {

                Button(
                    onClick = { onEvent(OnMaintenanceListEvent.OnButtonAddMaintenancePush) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.primaryContainer),
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Text(text = "Ajouter un acte d'entretien")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(id = R.color.primaryContainer)),
                    horizontalArrangement = Arrangement.End
                ) {
                    Column {
                        IconButton(onClick = { openSortChoice = !openSortChoice }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "")

                        }
                        DropdownMenu(
                            expanded = openSortChoice,
                            onDismissRequest = { openSortChoice = false }) {
                            SortType.values().forEach {
                                DropdownMenuItem(text = { Text(text = it.name) }, onClick = {
                                    openSortChoice = false
                                    onEvent(OnMaintenanceListEvent.OnSortMethodChanged(it))
                                })

                            }

                        }
                    }
                }
            }
            if (uiState.listUiState.isEmpty()) {
                Text(text = "Aucun acte d'entretien pour le moment")
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(uiState.listUiState.size) { item ->
                        ViewMaintenanceItem(uiState.listUiState[item])
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewServicing() {
    ViewListMaintenanceView(
        uiState = MaintenanceListUiState(listUiState = listOf(), isLoading = true),
        onEvent = {})
}
