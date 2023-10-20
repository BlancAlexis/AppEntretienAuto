package com.example.manageyourcar.composeView.common


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CustomDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Surface(
            modifier = Modifier
                .wrapContentSize()
                .wrapContentHeight()
                .border(BorderStroke(1.dp, Color.Blue), CircleShape),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Numéro VIN",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Un numéro VIN  est le numéro de châssis de la voiture.\u2028Il peut-être trouvé sur la carte grise de la voiture.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Row (horizontalArrangement = Arrangement.Center, modifier= Modifier.fillMaxWidth()){
                    Button(
                        onClick = { onDismiss() },
                    ) {
                        Text("Fermer")
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomDialog() {
    CustomDialog(
        onDismiss = {},
    )
}