package com.giraffe.myweatherapp.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.giraffe.myweatherapp.R
import com.giraffe.myweatherapp.ui.theme.MyWeatherAppTheme
import com.giraffe.myweatherapp.ui.theme.darkBlue
import com.giraffe.myweatherapp.ui.theme.fontFamily
import com.giraffe.myweatherapp.ui.theme.white

@Composable
fun HourCard(
    modifier: Modifier = Modifier,
    iconRes: Int = R.drawable.day_mainly_clear,
    temperature: Int = 25,
    hour: String = "11:00",
    backgroundColor: Color = white.copy(alpha = .7f),
    borderColor: Color = darkBlue.copy(alpha = .08f),
) {
    Box {
        Column(
            modifier = modifier
                .padding(top = 12.dp)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(color = backgroundColor, shape = RoundedCornerShape(24.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .offset(y = (-12).dp),
                painter = painterResource(iconRes),
                contentDescription = "icon"
            )
            Text(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 26.dp),
                text = "$temperature°C", style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                    color = darkBlue.copy(alpha = .87f)
                )
            )
            Text(
                modifier = Modifier.padding(bottom = 16.dp, start = 27.5.dp, end = 27.5.dp),
                text = hour,
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                    color = darkBlue.copy(alpha = .6f)
                )
            )
        }
    }
}

@Preview
@Composable
fun HourCardPreview() {
    MyWeatherAppTheme {
        HourCard()
    }
}