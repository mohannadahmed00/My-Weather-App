package com.giraffe.myweatherapp.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giraffe.myweatherapp.R
import com.giraffe.myweatherapp.presentation.model.DayUiModel
import com.giraffe.myweatherapp.ui.theme.MyWeatherAppTheme
import com.giraffe.myweatherapp.ui.theme.darkBlue
import com.giraffe.myweatherapp.ui.theme.fontFamily

@Composable
fun DayCard(
    modifier: Modifier = Modifier,
    day: DayUiModel = DayUiModel()
) {
    Row(
        modifier = modifier
            .height(61.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = day.dayName,
            style = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = darkBlue.copy(alpha = .6f)
            )
        )
        Image(
            modifier = Modifier.padding(horizontal = 40.dp),
            painter = painterResource(day.iconRes),
            contentDescription = "icon"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .size(12.dp)
                        .padding(end = 4.dp),
                    painter = painterResource(R.drawable.arrow_up),
                    contentScale = ContentScale.Crop,
                    contentDescription = "arrow up",
                    colorFilter = ColorFilter.tint(darkBlue.copy(alpha = .6f))
                )
                Text(
                    text = "${day.highTemperature}°C",
                    style = TextStyle(
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = darkBlue.copy(alpha = .6f)
                    )
                )
            }
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(1.dp)
                    .height(14.dp)
                    .background(
                        color = darkBlue.copy(alpha = .24f),
                        shape = RoundedCornerShape(15.dp)
                    )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .size(12.dp)
                        .padding(end = 4.dp),
                    painter = painterResource(R.drawable.arrow_down),
                    contentScale = ContentScale.Crop,
                    contentDescription = "arrow up",
                    colorFilter = ColorFilter.tint(darkBlue.copy(alpha = .6f))
                )
                Text(
                    text = "${day.lowTemperature}°C",
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
}

@Preview
@Composable
fun DayCardPreview() {
    MyWeatherAppTheme {
        DayCard()
    }
}