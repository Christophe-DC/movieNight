package com.cdcoding.movienight.movies.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cdcoding.movienight.database.data.model.Movie
import com.cdcoding.movienight.movies.R

@Composable
fun BannerMovies(
    modifier: Modifier = Modifier,
    movies: List<Movie>
) {
    Box(modifier = modifier)
    {
        BoxWithConstraints()
        {
            val boxWidth = this.maxWidth
            Column(Modifier.fillMaxWidth())
            {
                LazyRow(userScrollEnabled = false)
                {
                    itemsIndexed(movies) { index, movie ->
                        if (index == 0) {
                            val backdropPath =
                                "https://image.tmdb.org/t/p/w500${movie.backdropPath}"

                            AsyncImage(
                                modifier = Modifier
                                    .width(boxWidth * 0.8f),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(backdropPath)
                                    .crossfade(true)
                                    .build(),
                                contentScale = ContentScale.FillWidth,
                                error = painterResource(R.drawable.banner_placeholder),
                                placeholder = painterResource(R.drawable.banner_placeholder),
                                contentDescription = null,
                            )
                        } else {
                            Image(
                                modifier = Modifier
                                    .width(boxWidth * 0.5f)
                                    .padding(start = 6.dp),
                                contentScale = ContentScale.FillWidth,
                                painter = painterResource(id = R.drawable.banner_placeholder),
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
                        Text(
                            text = movies.firstOrNull()?.title ?: "",
                            style = MaterialTheme.typography.h1,
                            color = MaterialTheme.colors.onBackground,
                            maxLines = 2
                        )
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


