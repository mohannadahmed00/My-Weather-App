package com.giraffe.myweatherapp.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giraffe.myweatherapp.presentation.model.HourUiModel
import com.giraffe.myweatherapp.ui.theme.MyWeatherAppTheme
import com.giraffe.myweatherapp.ui.theme.fontFamily

@Composable
fun HourCard(
    modifier: Modifier = Modifier,
    hour: HourUiModel = HourUiModel(),
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    borderColor: Color = MaterialTheme.colorScheme.outline,
) {
    Column(
        modifier = modifier
            .padding(top = 12.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(20.dp)
            )
            .background(color = backgroundColor, shape = RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .height(58.dp)
                .padding(horizontal = 12.dp)
                .offset(y = (-12).dp),
            painter = painterResource(hour.iconRes),
            contentDescription = "icon"
        )
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 26.dp),
            text = "${hour.temperature}°C", style = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        Text(
            modifier = Modifier.padding(bottom = 16.dp, start = 27.5.dp, end = 27.5.dp),
            text = hour.time,
            style = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        )
    }
}


@Preview
@Composable
private fun Preview() {
    MyWeatherAppTheme {
        HourCard()
    }
}