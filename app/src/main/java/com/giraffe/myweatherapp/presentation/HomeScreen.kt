package com.giraffe.myweatherapp.presentation

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giraffe.myweatherapp.R
import com.giraffe.myweatherapp.presentation.composable.CurrentInfoCard
import com.giraffe.myweatherapp.presentation.composable.DayCard
import com.giraffe.myweatherapp.presentation.composable.DetailCard
import com.giraffe.myweatherapp.presentation.composable.HourCard
import com.giraffe.myweatherapp.presentation.composable.LocationCard
import com.giraffe.myweatherapp.presentation.composable.LocationRequirements
import com.giraffe.myweatherapp.presentation.model.HomeUiState
import com.giraffe.myweatherapp.ui.theme.MyWeatherAppTheme
import com.giraffe.myweatherapp.ui.theme.darkBlue
import com.giraffe.myweatherapp.ui.theme.fontFamily
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    HomeContent(state, viewModel)
}

@Composable
fun HomeContent(state: HomeUiState, events: HomeViewModel) {
    val scrollState = rememberScrollState()
    val screenHeightPx =
        with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp.toPx() }
    var isLarge by remember { mutableStateOf(true) }
    LaunchedEffect(scrollState.value) {
        isLarge = !(scrollState.value > screenHeightPx / 9.9)
    }
    val animateIconSize by animateSizeAsState(
        if (isLarge) Size(220.21f, 200f) else Size(124f, 112f)
    )
    val animateIconPosition by animateIntOffsetAsState(
        if (isLarge) IntOffset(0, 0) else IntOffset(-168, 88)
    )
    val animateInfoCardPosition by animateIntOffsetAsState(
        if (isLarge) IntOffset(0, 0) else IntOffset(168, -(143))
    )
    val animateAbove by animateIntOffsetAsState(
        if (isLarge) IntOffset(0, 0) else IntOffset(0, -(143))
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(vertical = 24.dp)
                .statusBarsPadding()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LocationCard(
                modifier = Modifier.clickable { isLarge = !isLarge },
                locationName = state.locationName
            )
            Image(
                modifier = Modifier
                    .padding(start = 65.dp, end = 65.dp, bottom = 12.dp)
                    .width(animateIconSize.width.dp)
                    .height(animateIconSize.height.dp)
                    .offset {
                        animateIconPosition
                    }
                    .shadow(150.dp, spotColor = MaterialTheme.colorScheme.surfaceBright),
                painter = painterResource(state.currentWeatherIcon),
                contentDescription = "icon",
            )
            CurrentInfoCard(
                modifier = Modifier
                    .offset {
                        animateInfoCardPosition
                    },
                temperature = state.currentTemperature,
                description = state.currentWeatherDescription,
                highTemperature = state.currentHighTemperature,
                lowTemperature = state.currentLowTemperature
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, top = 24.dp)
                    .offset { animateAbove },
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                DetailCard(modifier = Modifier.weight(1f), value = "${state.windSpeed} KM/h")
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
                    .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 24.dp)
                    .offset { animateAbove },
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
                    value = "${state.pressure.toInt()} hPa",
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
                    .padding(start = 12.dp, top = 24.dp, bottom = 12.dp)
                    .offset { animateAbove },
                text = "Today",
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
            LazyRow(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .offset { animateAbove },
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                items(state.hourlyTemperatures) { HourCard(hour = it) }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, bottom = 12.dp)
                    .offset { animateAbove },
                text = "Next 7 days",
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .offset { animateAbove }
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(24.dp)
                    )
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
        LocationRequirements(
            isLocationPermissionDialogVisible = state.isLocationPermissionDialogVisible,
            isLocationServiceDialogVisible = state.isLocationServiceDialogVisible,
            setLocationPermissionFlag = events::setLocationPermissionFlag,
            setLocationServiceFlag = events::setLocationServiceFlag,
        ) {
            events.getForecastOfCurrentLocation()
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MyWeatherAppTheme {
        HomeScreen()
    }
}