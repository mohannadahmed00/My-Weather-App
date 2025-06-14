package com.giraffe.myweatherapp.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giraffe.myweatherapp.presentation.model.DayUiModel
import com.giraffe.myweatherapp.ui.theme.MyWeatherAppTheme
import com.giraffe.myweatherapp.ui.theme.fontFamily

@Composable
fun DayCard(
    modifier: Modifier = Modifier,
    day: DayUiModel = DayUiModel(),
) {
    Row(
        modifier = modifier
            .height(61.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = day.dayName,
            style = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        )
        Image(
            modifier = Modifier
                .padding(horizontal = 9.5.dp)
                .weight(1f),
            painter = painterResource(day.iconRes),
            contentDescription = "icon"
        )
        TemperatureRangeCard(
            modifier = Modifier.weight(103 / 91f),
            highTemperature = day.highTemperature,
            lowTemperature = day.lowTemperature
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyWeatherAppTheme {
        DayCard()
    }
}