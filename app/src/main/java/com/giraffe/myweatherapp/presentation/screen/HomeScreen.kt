package com.giraffe.myweatherapp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giraffe.myweatherapp.R
import com.giraffe.myweatherapp.presentation.composable.DayCard
import com.giraffe.myweatherapp.presentation.composable.DetailCard
import com.giraffe.myweatherapp.presentation.composable.HourCard
import com.giraffe.myweatherapp.presentation.composable.TemperatureRangeCard
import com.giraffe.myweatherapp.ui.theme.MyWeatherAppTheme
import com.giraffe.myweatherapp.ui.theme.darkBlue
import com.giraffe.myweatherapp.ui.theme.fontFamily
import com.giraffe.myweatherapp.ui.theme.gray
import com.giraffe.myweatherapp.ui.theme.lightBlue
import com.giraffe.myweatherapp.ui.theme.white

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(listOf(lightBlue, white)))
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = 24.dp)
                .statusBarsPadding()
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.location),
                    contentDescription = "location",
                    colorFilter = ColorFilter.tint(gray)
                )
                Text(
                    text = "Baghdad",
                    style = TextStyle(
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = gray
                    )
                )
            }
            Image(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                painter = painterResource(R.drawable.day_mainly_clear),
                contentDescription = "icon",
            )
            Text(
                text = "24°C",
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 64.sp,
                    color = darkBlue
                )
            )
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = "Partly cloudy",
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                    color = darkBlue.copy(alpha = .6f)
                )
            )
            TemperatureRangeCard()
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 12.dp)
                    .height(250.dp),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                items(6) { DetailCard() }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 24.dp, bottom = 12.dp),
                text = "Today",
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 20.sp,
                    color = darkBlue
                )
            )
            LazyRow(
                modifier = Modifier.padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                items(6) { HourCard() }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, bottom = 12.dp),
                text = "Next 7 days",
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 20.sp,
                    color = darkBlue
                )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .border(
                        width = 1.dp,
                        color = darkBlue.copy(alpha = .08f),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .background(white.copy(alpha = .6f), shape = RoundedCornerShape(24.dp))
            ) {
                (0..6).forEach {
                    DayCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    if (it != 6) Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(darkBlue.copy(alpha = .08f))
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MyWeatherAppTheme {
        HomeScreen()
    }
}