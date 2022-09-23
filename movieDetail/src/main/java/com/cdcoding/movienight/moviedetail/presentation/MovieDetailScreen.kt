package com.cdcoding.movienight.moviedetail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cdcoding.movienight.moviedetail.R
import com.cdcoding.movienight.moviedetail.presentation.component.VideoPlayer
import java.util.concurrent.TimeUnit

@Composable
fun MovieDetailScreen(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(it)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 32.dp,
                        bottom = 48.dp
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    Column(modifier = Modifier.fillMaxSize())
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            if (state.movieDetail?.videoUrl != null) {
                                val url =
                                    "https://www.youtube.com/watch?v=${state.movieDetail.videoUrl}"
                                VideoPlayer(sourceUrl = url)
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 22.dp,
                                    top = 28.dp,
                                    end = 58.dp
                                )
                        ) {
                            if (state.movieDetail?.title != null) {
                                Text(
                                    text = state.movieDetail.title!!,
                                    style = MaterialTheme.typography.h1
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 22.dp,
                                    top = 18.dp,
                                    end = 22.dp
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(0.75f)
                            ) {
                                val genres = state.movieDetail?.genres?.joinToString() ?: ""
                                Text(
                                    text = genres,
                                    style = MaterialTheme.typography.subtitle1
                                )
                            }
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.Top
                            ) {
                                val minutes = state.movieDetail?.runtime ?: 0
                                val hours = TimeUnit.MINUTES.toHours(minutes.toLong())
                                val remainMinutes = minutes - TimeUnit.HOURS.toMinutes(hours)
                                val movieDuration =
                                    String.format("%2dh %02dm", hours, remainMinutes)

                                Text(
                                    text = movieDuration,
                                    style = MaterialTheme.typography.body2
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 22.dp,
                                    top = 36.dp,
                                    end = 22.dp
                                )
                        ) {
                            Column {
                                Button(
                                    modifier = Modifier.height(40.dp),
                                    onClick = {}
                                ) {
                                    Column {
                                        Icon(
                                            modifier = Modifier
                                                .width(20.dp)
                                                .height(20.dp),
                                            imageVector = Icons.Default.PlayArrow,
                                            contentDescription = null
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = stringResource(id = R.string.watch),
                                            style = MaterialTheme.typography.button
                                        )
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .background(
                                        MaterialTheme.colors.primary.copy(
                                            alpha = 0.2f
                                        ),
                                        shape = shapes.small
                                    )
                                    .width(40.dp)
                                    .height(40.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(20.dp),
                                    imageVector = Icons.Default.StarOutline,
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.primary
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 22.dp,
                                    top = 18.dp,
                                    end = 22.dp
                                )
                        ) {
                            val overview = state.movieDetail?.overview ?: ""
                            Text(
                                text = overview,
                                style = MaterialTheme.typography.body2
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 18.dp,
                                    bottom = 18.dp
                                )
                        ) {
                            val backdropsPath = state.movieDetail?.backdropsPath
                            if (backdropsPath != null) {
                                LazyRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentPadding = PaddingValues(
                                        start = 22.dp,
                                        end = 22.dp
                                    ),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    items(backdropsPath) { backdropsPath ->
                                        val posterPath =
                                            "https://image.tmdb.org/t/p/w500${backdropsPath}"

                                        AsyncImage(
                                            modifier = Modifier
                                                .height(60.dp)
                                                .shadow(10.dp),
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(posterPath)
                                                .crossfade(true)
                                                .build(),
                                            contentScale = ContentScale.FillWidth,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.TopEnd
                    )
                    {
                        Icon(
                            modifier = Modifier
                                .clickable {
                                    navController.navigateUp()
                                }
                                .size(32.dp)
                                .background(
                                    color = Color.Black,
                                    shape = CircleShape
                                ),
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}