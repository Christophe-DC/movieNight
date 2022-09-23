package com.cdcoding.movienight.movies.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cdcoding.movienight.database.data.model.Movie
import com.cdcoding.movienight.movies.R


@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Box(modifier = modifier)
    {
            val posterPath = "https://image.tmdb.org/t/p/w500${movie.posterPath}"

            AsyncImage(
                modifier = Modifier
                    .width(80.dp)
                    .shadow(10.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(posterPath)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillWidth,
                error = painterResource(R.drawable.poster_placeholder),
                placeholder = painterResource(R.drawable.poster_placeholder),
                contentDescription = null,
            )
    }
}


