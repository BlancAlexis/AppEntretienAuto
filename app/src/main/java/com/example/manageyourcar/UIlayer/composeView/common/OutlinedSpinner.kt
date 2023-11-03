package com.example.manageyourcar.UIlayer.composeView.common

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.manageyourcar.UIlayer.composeView.AddVehiculeMaintenanceView
import com.example.manageyourcar.UIlayer.composeView.UIState.AddVehiculeMaintenanceUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedSpinner(
    listMaintenanceName : List<String>,
    textLabel : String,
    onItemSelect : () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(listMaintenanceName[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text(textLabel) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            listMaintenanceName.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    onClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOutlinedSpinner() {
    OutlinedSpinner(listOf("Vidange","Pneus","Baterrie"),"Opérations")
}