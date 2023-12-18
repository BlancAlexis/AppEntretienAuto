package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.composeView.UIState.MaintenanceListUiState
import com.example.manageyourcar.UIlayer.composeView.UIState.SortType
import com.example.manageyourcar.UIlayer.composeView.common.CustomDialog
import com.example.manageyourcar.UIlayer.viewmodel.onMaintenanceListEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaintenanceListView(
    uiState: MaintenanceListUiState,
    onEvent: (onMaintenanceListEvent) -> Unit = {}
) {
    var openSortChoice by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.primaryColor)),
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

        Button(onClick = { onEvent(onMaintenanceListEvent.onButtonAddMaintenancePush) }, colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primaryContainer), contentColor = Color.Black), modifier = Modifier.padding(bottom = 20.dp)) {
            Text(text = "Ajouter un acte d'entretien")
        }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.primaryContainer)),
                horizontalArrangement = Arrangement.End
            ) {
                Column {
                IconButton(onClick = { openSortChoice=!openSortChoice }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "")

                }
                DropdownMenu(expanded = openSortChoice, onDismissRequest = { openSortChoice= false }) {
                    SortType.values().forEach {
                        DropdownMenuItem(text  = {Text(text = it.name)}, onClick = {
                            openSortChoice = false
                            onEvent(onMaintenanceListEvent.onSortMethodChanged(it))})

                    }

                }
            }
            }
                }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(uiState.listUiState.size) { item ->
                     MaintenanceViewItem(uiState.listUiState[item])
                }
            }
        }
    }
   // }
    }
@Preview(showBackground = true)
@Composable
fun PreviewServicing() {
    MaintenanceListView(
        uiState = MaintenanceListUiState(listUiState = listOf(), isLoading = true) ,
        onEvent = {})
}
