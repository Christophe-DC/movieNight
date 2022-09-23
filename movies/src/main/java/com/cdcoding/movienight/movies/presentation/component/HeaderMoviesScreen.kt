package com.cdcoding.movienight.movies.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cdcoding.movienight.common.R
import com.cdcoding.movienight.database.data.model.Account


@Composable
fun HeaderMoviesScreen(
    modifier: Modifier = Modifier,
    account: Account?,
    delayMillis: Int = 1000,
    durationMillis: Int = 1000
) {
    var horizontalBias by rememberSaveable { mutableStateOf(0f) }
    val horizontalAnimate = remember { Animatable(horizontalBias) }
    val alignment by derivedStateOf { BiasAlignment.Horizontal(horizontalAnimate.value) }
    LaunchedEffect(true) {
        if (horizontalBias > -1f) {
            horizontalAnimate.animateTo(
                targetValue = -1f,
                animationSpec = tween(
                    durationMillis = durationMillis,
                    delayMillis = delayMillis
                )
            )
            horizontalBias = -1f
        }
    }
    Box(modifier = modifier)
    {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = alignment,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .width(80.dp),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.movienight_title),
                contentDescription = null,
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colors.secondary,
                        shape = CircleShape
                    )
                    .size(28.dp),
                contentAlignment = Alignment.Center
            )
            {
                if (account != null) {
                    Text(
                        text = "${account.firstName.first().uppercase()}${
                            account.lastName.first().uppercase()
                        }",
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    }
}


