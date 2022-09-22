package com.cdcoding.movienight.movies.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.cdcoding.movienight.movies.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cdcoding.movienight.common.util.Screen
import com.cdcoding.movienight.movies.presentation.component.BannerMovies
import com.cdcoding.movienight.movies.presentation.component.MovieItem
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MoviesScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val mContext = LocalContext.current
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is MoviesViewModel.UiEvent.ShowToast -> {
                    Toast.makeText(mContext, event.message, Toast.LENGTH_LONG).show()
                }
                is MoviesViewModel.UiEvent.MovieDetailFetched -> {
                    /*
                    navController.navigate(
                        Screen.MovieDetailScreen.route +
                                "?movieDetailId=${event.movieDetailId}"
                    )*/
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .padding(start = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    BannerMovies(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 12.dp,
                                end = 20.dp
                            ),
                        account = state.account
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    BannerMovies(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 24.dp
                            ),
                        movies = state.movies
                    )
                }
                val moviePopular = state.movies.sortedByDescending { movie ->
                    movie.popularity
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.most_popular),
                        style = MaterialTheme.typography.body1
                    )
                }
                Row(modifier = Modifier.fillMaxWidth())
                {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(top = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(moviePopular) { movie ->
                            MovieItem(
                                modifier = Modifier.clickable {
                                    viewModel.onEvent(
                                        MoviesEvent.OpenMovieDetail(
                                            movie.id
                                        )
                                    )
                                },
                                movie = movie
                            )
                        }
                    }
                }
                val movieUpcoming = state.movies.filter { movie ->
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    if (movie.releaseDate == null) return@filter false
                    val releaseDate = dateFormat.parse(movie.releaseDate!!) ?: return@filter false
                    releaseDate.after(Date())
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.upcoming_movies),
                        style = MaterialTheme.typography.body1
                    )
                }
                Row(modifier = Modifier.fillMaxWidth())
                {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(top = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(movieUpcoming) { movie ->
                            MovieItem(
                                movie = movie
                            )
                        }
                    }
                }
                val movieTopRate = state.movies.sortedByDescending { movie ->
                    movie.voteAverage
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.top_rated),
                        style = MaterialTheme.typography.body1
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .fillMaxWidth()
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(top = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(movieTopRate) { movie ->
                            MovieItem(
                                movie = movie
                            )
                        }
                    }
                }
            }
        }
    }
}