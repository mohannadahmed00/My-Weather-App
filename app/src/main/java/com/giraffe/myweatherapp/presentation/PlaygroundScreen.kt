package com.giraffe.myweatherapp.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateSize
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.giraffe.myweatherapp.R
import com.giraffe.myweatherapp.ui.theme.green
import com.giraffe.myweatherapp.ui.theme.lightBlue
import com.giraffe.myweatherapp.ui.theme.lightShadowColor
import com.giraffe.myweatherapp.ui.theme.red
import com.giraffe.myweatherapp.ui.theme.white
import com.giraffe.myweatherapp.ui.theme.yellow

@Composable
fun PlaygroundScreen(modifier: Modifier = Modifier) {
    val state = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenHeightPx = with(LocalDensity.current) { configuration.screenHeightDp.dp.toPx() }
    LaunchedEffect(state.value) {
        Log.d("ScrollCheck", "Scrolled paslooool ${state.value}")

        if (state.value > screenHeightPx / 2) {
            // Do something when scroll passes half screen
            Log.d("ScrollCheck", "Scrolled past half of screen")
        }
    }
    var isYellowCircleVisible by remember { mutableStateOf(true) }
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = red,
        targetValue = lightShadowColor,
        animationSpec = InfiniteRepeatableSpec(
            tween(500),
            repeatMode = RepeatMode.Reverse)
    )
    val floatTransition by infiniteTransition.animateFloat(
        initialValue = 40f,
        targetValue = 140f,
        animationSpec = InfiniteRepeatableSpec(
            tween(500),
            repeatMode = RepeatMode.Reverse)
    )
    val transition = updateTransition(isYellowCircleVisible)
    //transition.animateSize {  }

    val animateSize by animateDpAsState(if (isYellowCircleVisible) 100.dp else 300.dp )
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(green.copy(alpha = .3f))
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(state)
                    .fillMaxWidth()
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .width(animateSize)
                        .background(color, shape = CircleShape),
                )
                Crossfade(
                    animationSpec = tween(3000),
                    targetState = isYellowCircleVisible){
                    when(it){
                        true -> Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .background(lightBlue, shape = CircleShape)
                        )
                        false -> Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .background(red, shape = CircleShape)
                        )
                    }

                }

                AnimatedVisibility(
                    isYellowCircleVisible,
                    enter = fadeIn(),
                    exit= fadeOut(
                        animationSpec = tween(4000)
                    ),

                ){
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .background(yellow, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            modifier = Modifier
                                .size(100.dp)
                                .animateEnterExit(
                                    enter = slideInHorizontally(),
                                    exit = slideOut(
                                        animationSpec = tween(4000)
                                    ) { IntOffset(it.width, -it.height) },
                                ),
                            imageVector = Icons.AutoMirrored.Default.ArrowForward, contentDescription = "")
                    }
                }
                (1..10).forEach {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(if (it < 5) red else green)
                    )
                }
            }
        }
        Button({ isYellowCircleVisible = !isYellowCircleVisible }) {
            Text("Hide the yellow circle")
        }
    }
}


/**/