package com.cdcoding.movienight.login.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.cdcoding.movienight.common.R


@Composable
fun AppTitle(
    modifier: Modifier = Modifier,
    delayMillis: Int = 1000,
    durationMillis: Int = 1000
) {
    Box(
        modifier = modifier
    ) {
        val verticalBias = remember { Animatable(-0.25f) }
        val alignment by derivedStateOf { BiasAlignment.Vertical(verticalBias.value) }
        val scale = remember {
            Animatable(2f)
        }
        LaunchedEffect(key1 = true) {
            launch {
                verticalBias.animateTo(
                    targetValue = -1f,
                    animationSpec = tween(
                        durationMillis = durationMillis,
                        delayMillis = delayMillis
                    )
                )
            }
            launch {
                scale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = durationMillis,
                        delayMillis = delayMillis
                    )
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp),
            verticalAlignment = alignment,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .width(80.dp)
                    .scale(scale.value),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.movienight_title),
                contentDescription = null,
            )
        }
    }
}

