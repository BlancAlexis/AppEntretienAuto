package com.example.manageyourcar.UIlayer.composeView

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SerivcingViewItem(

) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        shape = RoundedCornerShape(7.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth()
                    .background(Color.Blue)
            ) {
                Text(text = "Corsa D GSI", modifier = Modifier.padding(5.dp, 6.dp))
            }
            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)

            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Changement plaquette de frein")
                    Text(text = "12000km")
                }

                LinearProgressIndicator(
                    color = Color.Gray,
                    trackColor = Color.Red,
                    progress = 0.5f,
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .border(1.dp, Color.Transparent, CircleShape)

                )

            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun PreviewServicingItem() {
    SerivcingViewItem()
}
