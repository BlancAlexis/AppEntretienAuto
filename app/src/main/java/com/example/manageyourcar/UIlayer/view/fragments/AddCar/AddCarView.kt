package com.example.manageyourcar.UIlayer.view.fragments.AddCar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.UIState.AddCarUIState
import com.example.manageyourcar.UIlayer.view.common.CustomDialog
import com.example.manageyourcar.UIlayer.view.common.CustomPlaqueImmat
import com.example.manageyourcar.UIlayer.view.common.CustomTextField
import com.example.manageyourcar.UIlayer.viewmodel.OnCarRequest

@Composable
fun AddCarView(
    uiState: AddCarUIState,
    onEvent: (OnCarRequest) -> Unit = {}
) {
    uiState.carLocalFind?.let {
        CustomDialog(
            title = stringResource(id = R.string.if_your_car),
            content = "${uiState.carLocalFind.model}",
            onApprove = { onEvent(OnCarRequest.OnClickAddCarButton) },
            onDismiss = { onEvent(OnCarRequest.OnDismissAddCarFragment) })
    }
    var openDialog by remember { mutableStateOf(false) }
    if (openDialog) {
        CustomDialog(
            title = stringResource(id = R.string.VIN_number),
            content = stringResource(id = R.string.VIN_number_description),
            onDismiss = { openDialog = false }
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            IconButton(
                onClick = { onEvent(OnCarRequest.OnDismissAddCarFragment) }
            ) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
            }
        }

        Text(text = stringResource(R.string.enter_your_immat))
        CustomPlaqueImmat(
            registration = uiState.inputImmat,
            onImmatEvent = { onEvent(OnCarRequest.OnRegistrationNumberChanged(it ?: "")) }
        )
        Button(onClick = { onEvent(OnCarRequest.OnClickSearchCarByRegistrationNumberButton) }) {
            Text(
                text = stringResource(R.string.find), fontSize = 20.sp
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            IconButton(
                onClick = { openDialog = true },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(bottom = 40.dp)
            ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "Close")
            }
            CustomTextField(
                onValueChange = { onEvent(OnCarRequest.OnVINNumberChanged(it)) },
                textFieldValue = uiState.inputVIN ?: "",
                label = stringResource(R.string.find_by_VIN),
                readOnly = false,
                keyboardType = KeyboardType.Text,
                labelTextStyle = TextStyle(
                    color = Color.Black,
                ),
                modifier = Modifier.align(Alignment.BottomCenter)
            )


        }

        Button(onClick = { onEvent(OnCarRequest.OnClickSearchCarWithSIVButton) }) {
            Text(
                text = "Rechercher", fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddCarView() {
    AddCarView(AddCarUIState())
}