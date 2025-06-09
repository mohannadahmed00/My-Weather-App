package com.giraffe.myweatherapp.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giraffe.myweatherapp.ui.theme.MyWeatherAppTheme
import com.giraffe.myweatherapp.ui.theme.fontFamily

@Composable
fun CurrentInfoCard(
    modifier: Modifier = Modifier,
    temperature: Double = 24.0,
    description: String = "Partly cloudy",
    highTemperature: Double = 32.0,
    lowTemperature: Double = 20.0,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${temperature.toInt()}°C",
            style = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.W600,
                fontSize = 64.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        )
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            text = description,
            style = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        )
        Box(
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(100.dp)
            )
        ) {
            TemperatureRangeCard(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                highTemperature = highTemperature,
                lowTemperature = lowTemperature,
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MyWeatherAppTheme {
        CurrentInfoCard()
    }
}