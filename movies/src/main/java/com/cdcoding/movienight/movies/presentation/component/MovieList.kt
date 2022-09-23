package com.cdcoding.movienight.movies.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cdcoding.movienight.database.data.model.Movie

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    label: String = "",
    contentPaddingHorizontal: Dp = 0.dp,
    onMovieItemClick: (movieId: Int?) -> Unit,
) {
    Column(modifier = modifier)
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = contentPaddingHorizontal)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.body1
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
        )
        {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = contentPaddingHorizontal,
                    end = contentPaddingHorizontal
                ),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(movies) { movie ->
                    MovieItem(
                        modifier = Modifier.clickable {
                            onMovieItemClick(movie.id)
                        },
                        movie = movie
                    )
                }
            }
        }
    }
}