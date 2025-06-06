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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.giraffe.myweatherapp.presentation.viewmodel.HomeUiState
import com.giraffe.myweatherapp.presentation.viewmodel.HomeViewModel
import com.giraffe.myweatherapp.ui.theme.MyWeatherAppTheme
import com.giraffe.myweatherapp.ui.theme.darkBlue
import com.giraffe.myweatherapp.ui.theme.fontFamily
import com.giraffe.myweatherapp.ui.theme.gray
import com.giraffe.myweatherapp.ui.theme.lightBlue
import com.giraffe.myweatherapp.ui.theme.white
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    HomeContent(state)
}

@Composable
fun HomeContent(state: HomeUiState) {
    Box(
        modifier = Modifier
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
                    text = state.locationName,
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
                painter = painterResource(state.currentWeatherIcon),
                contentDescription = "icon",
            )
            Text(
                text = "${state.currentTemperature}°C",
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 64.sp,
                    color = darkBlue
                )
            )
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = state.currentWeatherDescription,
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                    color = darkBlue.copy(alpha = .6f)
                )
            )
            TemperatureRangeCard(
                highTemperature = state.currentHighTemperature,
                lowTemperature = state.currentLowTemperature
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, top = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                DetailCard(modifier = Modifier.weight(1f), value = "${state.windFast} KM/h")
                DetailCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.humidity,
                    value = "${state.humidity}%",
                    label = "Humidity"
                )
                DetailCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.rain,
                    value = "${state.rain}%",
                    label = "Rain"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                DetailCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.uv,
                    value = "${state.humidity}%",
                    label = "Humidity"
                )
                DetailCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.pressure,
                    value = "${state.rain} hPa",
                    label = "Pressure"
                )
                DetailCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.temperature,
                    value = "${state.feelsLike}°C",
                    label = "Feels like"
                )
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
                items(state.hourlyTemperatures) { HourCard(hour = it) }
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
                state.dailyTemperatures.forEachIndexed { index, item ->
                    DayCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        day = item
                    )
                    if (index != state.dailyTemperatures.size - 1) Spacer(
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