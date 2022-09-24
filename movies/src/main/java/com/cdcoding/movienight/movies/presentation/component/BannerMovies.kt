package com.cdcoding.movienight.movies.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cdcoding.movienight.database.data.model.Movie
import kotlinx.coroutines.delay

@OptIn(ExperimentalMotionApi::class, ExperimentalAnimationApi::class)
@Composable
fun BannerMovies(
    modifier: Modifier = Modifier,
    movies: List<Movie>
) {
    val state: LazyListState = rememberLazyListState()
    val firstItemVisible by remember {
        derivedStateOf {
            state.layoutInfo.visibleItemsInfo.firstOrNull()
        }
    }

    val itemOffset = firstItemVisible?.offset ?: 0
    val itemSize = firstItemVisible?.size?.toFloat() ?: 0f
    val progress = if (itemSize > 0) -itemOffset / itemSize else 0f

    val firstVisibleItemIndex by remember { derivedStateOf { state.firstVisibleItemIndex } }
    val titleIndex = if (itemOffset < 0) {
        firstVisibleItemIndex + 1
    } else {
        firstVisibleItemIndex
    }
    val movieTitle = if (movies.size > titleIndex) movies[titleIndex].title ?: ""
    else ""


    LaunchedEffect(true) {
        repeat(Int.MAX_VALUE) {
            delay(3000L)
            val visibleItemOffset = state.layoutInfo.visibleItemsInfo.firstOrNull()?.offset ?: 0
            val visibleItemSize =
                state.layoutInfo.visibleItemsInfo.firstOrNull()?.size?.toFloat() ?: 0f
            val currentIndex = state.firstVisibleItemIndex
            val scrollValue = visibleItemSize + visibleItemOffset

            if (currentIndex >= state.layoutInfo.totalItemsCount - 1) {
                state.scrollToItem(0)
            } else {
                state.animateScrollBy(
                    value = scrollValue,
                    animationSpec =
                    tween(500)
                )
            }
        }
    }

    val csStart = ConstraintSet(
        """
            { 
                banner_pic: {
                        width: '60%',
                        height: '16:9',
                        alpha: 0.0,
                        start: ["parent", "start", 0],
                        top: ["parent", "top", 0]
                      },
                banner_background: {
                        width: 'spread',
                        height: 'spread',
                        start: ["banner_pic", "start"],
                        top: ["banner_pic", "top"],
                        end: ["banner_pic", "end"],
                        bottom: ["banner_pic", "bottom"]
                      }
            }
            """
    )
    val csMiddle = ConstraintSet(
        """
            { 
                banner_pic: {
                        width: '100%',
                        height: '16:9',
                        alpha: 1.0,
                        start: ["parent", "start", 0],
                        top: ["parent", "top", 0]
                      },
                banner_background: {
                        width: 'spread',
                        height: 'spread',
                        start: ["banner_pic", "start"],
                        top: ["banner_pic", "top"],
                        end: ["banner_pic", "end"],
                        bottom: ["banner_pic", "bottom"]
                      }
            }
            """
    )

    val csEnd = ConstraintSet(
        """
            { 
                banner_pic: {
                        width: '60%',
                        height: '16:9',
                        alpha: 0.0,
                        start: ["parent", "end", 0],
                        top: ["parent", "top", 0]
                      },
                banner_background: {
                        width: 'spread',
                        height: 'spread',
                        start: ["banner_pic", "start"],
                        top: ["banner_pic", "top"],
                        end: ["banner_pic", "end"],
                        bottom: ["banner_pic", "bottom"]
                      }
            }
            """
    )


    Box(modifier = modifier)
    {
        BoxWithConstraints()
        {
            val boxWidth = this.maxWidth
            Column(Modifier.fillMaxWidth())
            {
                LazyRow(
                    state = state,
                    userScrollEnabled = false,
                    contentPadding = PaddingValues(
                        end = boxWidth * 0.2f
                    )
                )
                {
                    itemsIndexed(movies) { index, movie ->
                        var start by remember { mutableStateOf(csStart) }
                        var end by remember { mutableStateOf(csMiddle) }
                        when (firstVisibleItemIndex) {
                            index -> {
                                start = csMiddle
                                end = csEnd
                            }
                            index - 1 -> {
                                start = csStart
                                end = csMiddle
                            }
                            else -> {
                                start = csStart
                                end = csStart
                            }

                        }

                        val backdropPath = "https://image.tmdb.org/t/p/w500${movie.backdropPath}"
                        MotionLayout(
                            start = start,
                            end = end,
                            progress = progress,
                            modifier = Modifier
                                .width(boxWidth * 0.8f + 12.dp)
                                .height(boxWidth * 0.45f)
                                .padding(end = 12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .layoutId("banner_background")
                                    .background(Color(4, 6, 36))
                            )

                            AsyncImage(
                                modifier = Modifier.layoutId("banner_pic"),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(backdropPath)
                                    .crossfade(true)
                                    .build(),
                                alignment = Alignment.TopEnd,
                                contentScale = ContentScale.FillWidth,
                                contentDescription = null,
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .width(boxWidth * 0.8f + 30.dp)
                        .offset(y = (-60).dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(
                        modifier = Modifier
                            .width(boxWidth * 0.6f)
                            .padding(start = 18.dp),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        AnimatedContent(
                            targetState = movieTitle
                        ) { targetTitle ->
                            Text(
                                text = targetTitle,
                                style = MaterialTheme.typography.h1,
                                color = MaterialTheme.colors.onBackground,
                                maxLines = 2
                            )
                        }

                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Box(
                            modifier = Modifier
                                .width(60.dp)
                                .height(38.dp)
                                .background(
                                    Color.White,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Default.PlayArrow,
                                tint = DarkGray,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
        }
    }
}


