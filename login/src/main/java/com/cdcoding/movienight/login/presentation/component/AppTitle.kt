package com.cdcoding.movienight.login.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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

        var runAnimation by rememberSaveable { mutableStateOf(true) }
        val verticalBias = remember {
            Animatable(
                if (runAnimation) -0.25f
                else -1f
            )
        }
        val alignment by derivedStateOf { BiasAlignment.Vertical(verticalBias.value) }
        val scale = remember {
            Animatable(
                if (runAnimation) 2f
                else 1f
            )
        }
        LaunchedEffect(runAnimation) {
            launch {
                if (runAnimation) {
                    verticalBias.animateTo(
                        targetValue = -1f,
                        animationSpec = tween(
                            durationMillis = durationMillis,
                            delayMillis = delayMillis
                        )
                    )
                }
            }
            launch {
                if (runAnimation) {
                    scale.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(
                            durationMillis = durationMillis,
                            delayMillis = delayMillis
                        )
                    )
                    runAnimation = false
                }
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

