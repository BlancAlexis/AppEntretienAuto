package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.manageyourcar.UIlayer.composeView.UIState.BluetoothUiState
import com.example.manageyourcar.UIlayer.composeView.UIState.ServicingUIState
import com.example.manageyourcar.UIlayer.viewmodel.onBluetoothDeviceEvent
import com.example.manageyourcar.domainLayer.bluetooth.BluetoothDevice

@Composable
fun BluetoothDeviceItemView(uiState: BluetoothDevice) {
    Card {
        Column {
            Row {
                Text(text = uiState.address)
            }
            Row {
                uiState.name?.let { Text(text = it) }
            }
        }

    }

}

@Composable
fun BluetoothDeviceView (
    uiState: BluetoothUiState,
    onEvent: (onBluetoothDeviceEvent) -> Unit = {})
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Button(onClick = { onEvent(onBluetoothDeviceEvent.OnBluetoothDeviceScanClick) }) {
                Text(text = "Start scan")
            }
            Button(onClick = { onEvent(onBluetoothDeviceEvent.OnBluetoothDeviceStopScanClick) }) {
                Text(text = "End scan")
            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Text(text = "Available devices")
            }
            itemsIndexed(uiState.scannedDevices) { index, item ->
                Button(onClick = { onEvent(onBluetoothDeviceEvent.OnBluetoothDeviceClick(item)) }) {
                BluetoothDeviceItemView(item )}
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                 Text(text = "Paired devices")            }
            items(uiState.pairedDevices.size) { item ->
                BluetoothDeviceItemView(uiState.pairedDevices[item] )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBluetoothDevice() {
    ServicingView(
        uiState = listOf(ServicingUIState()),
        onEvent = {})
}
