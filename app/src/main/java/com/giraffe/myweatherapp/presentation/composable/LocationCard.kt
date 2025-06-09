package com.giraffe.myweatherapp.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giraffe.myweatherapp.R
import com.giraffe.myweatherapp.ui.theme.MyWeatherAppTheme
import com.giraffe.myweatherapp.ui.theme.fontFamily

@Composable
fun LocationCard(
    modifier: Modifier = Modifier,
    locationName: String = "Cairo",
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.location),
            contentDescription = "location",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary)
        )
        Text(
            text = locationName,
            style = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondary
            )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyWeatherAppTheme {
        LocationCard()
    }
}