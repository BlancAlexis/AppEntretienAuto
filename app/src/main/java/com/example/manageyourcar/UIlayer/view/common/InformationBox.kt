package com.example.manageyourcar.UIlayer.view.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manageyourcar.R

@Composable
fun InformationBox(
    title: String,
    content: String,
    icon: Painter,
) {
    val juraFamily = FontFamily(
        Font(R.font.jura, FontWeight.Medium)
    )

    Row(
        Modifier
            .background(
                color = Color(0, 29, 54),
                shape = RoundedCornerShape(10.dp)
            )
            .width(150.dp)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),

        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            tint = Color.White,
        )
        Column(
            Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontFamily = juraFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = content,
                color = Color.White,
                fontFamily = juraFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}