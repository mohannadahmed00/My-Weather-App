package com.giraffe.myweatherapp.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
fun DetailCard(
    modifier: Modifier = Modifier,
    iconRes: Int = R.drawable.fast_wind,
    value: String = "13 KM/h",
    label: String = "Wind",
    backgroundColor: Color = white.copy(alpha = .7f),
    borderColor: Color = darkBlue.copy(alpha = .08f),
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(24.dp)
            )
            .background(color = backgroundColor, shape = RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier.padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.padding(bottom = 8.dp),
                painter = painterResource(iconRes),
                contentDescription = "icon"
            )
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = value, style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 20.sp,
                    color = darkBlue.copy(alpha = .87f)
                )
            )
            Text(
                text = label, style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = darkBlue.copy(alpha = .6f)
                )
            )
        }
    }
}

@Preview
@Composable
fun DetailCardPreview() {
    MyWeatherAppTheme {
        DetailCard()
    }
}