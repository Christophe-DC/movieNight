package com.cdcoding.movienight.login.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.cdcoding.movienight.login.R


@Composable
fun BackgroundContainer(
    delayMillis: Int = 1000,
    durationMillis: Int = 1000
) {
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    var backgroundImageHeight by remember { mutableStateOf(0) }
    val offsetY = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        offsetY.animateTo(
            targetValue = configuration.screenHeightDp.dp.value - backgroundImageHeight.toFloat(),
            animationSpec = tween(
                durationMillis = durationMillis,
                delayMillis = delayMillis
            )
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { size ->
                backgroundImageHeight = size.height
            }
            .verticalScroll(scrollState, false)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .offset {
                    IntOffset(
                        0,
                        offsetY.value.toInt()
                    )
                },
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopCenter,
            painter = painterResource(id = R.drawable.background_movie),
            contentDescription = null,
        )
    }
}
