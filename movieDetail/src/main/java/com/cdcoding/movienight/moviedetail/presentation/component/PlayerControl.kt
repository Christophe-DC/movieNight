package com.cdcoding.movienight.moviedetail.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun PlayerControl(
    modifier: Modifier = Modifier,
    isPlaying: () -> Boolean,
) {
    val isVideoPlaying = remember(isPlaying()) { isPlaying() }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (!isVideoPlaying) {
            Icon(
                modifier = Modifier.size(100.dp),
                imageVector = Icons.Default.PlayCircleOutline,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}