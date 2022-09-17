package com.cdcoding.movienight.login.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.cdcoding.movienight.login.R
import kotlinx.coroutines.launch


@Composable
fun AppTitle(
    modifier: Modifier = Modifier,
    delayMillis: Int = 1000,
    durationMillis: Int = 1000
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val offsetY = remember {
            Animatable(constraints.maxHeight / 2f - 150f)
        }
        val scale = remember {
            Animatable(1f)
        }
        LaunchedEffect(key1 = true) {
            launch {
                offsetY.animateTo(
                    targetValue = 160f,
                    animationSpec = tween(
                        durationMillis = durationMillis,
                        delayMillis = delayMillis
                    )
                )
            }
            launch {
                scale.animateTo(
                    targetValue = 0.5f,
                    animationSpec = tween(
                        durationMillis = durationMillis,
                        delayMillis = delayMillis
                    )
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .width(150.dp)
                    .offset {
                        IntOffset(
                            0,
                            offsetY.value.toInt()
                        )
                    }
                    .scale(scale.value),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.movienight_tittle),
                contentDescription = null,
            )
        }
    }
}

