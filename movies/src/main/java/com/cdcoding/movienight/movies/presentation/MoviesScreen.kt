package com.cdcoding.movienight.movies.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
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
import com.cdcoding.movienight.movies.presentation.component.HeaderMoviesScreen
import com.cdcoding.movienight.movies.presentation.component.MovieList
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
                    navController.navigate(
                        Screen.MovieDetailScreen.route +
                                "?movieDetailId=${event.movieDetailId}"
                    )
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
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp)
                ) {
                    HeaderMoviesScreen(
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
                        .padding(
                            top = 24.dp,
                            start = 20.dp
                        )
                ) {
                    BannerMovies(
                        modifier = Modifier
                            .fillMaxWidth(),
                        movies = state.movies
                    )
                }
                val moviePopular = state.movies.sortedByDescending { movie ->
                    movie.popularity
                }
                Row(modifier = Modifier.fillMaxWidth())
                {
                    MovieList(
                        movies = moviePopular,
                        label = stringResource(id = R.string.most_popular),
                        contentPaddingHorizontal = 20.dp,
                        onMovieItemClick = { movieId ->
                            viewModel.onEvent(
                                MoviesEvent.OpenMovieDetail(
                                    movieId
                                )
                            )
                        }
                    )
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
                    MovieList(
                        movies = movieUpcoming,
                        label = stringResource(id = R.string.upcoming_movies),
                        contentPaddingHorizontal = 20.dp,
                        onMovieItemClick = { movieId ->
                            viewModel.onEvent(
                                MoviesEvent.OpenMovieDetail(
                                    movieId
                                )
                            )
                        }
                    )
                }
                val movieTopRate = state.movies.sortedByDescending { movie ->
                    movie.voteAverage
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 24.dp,
                            bottom = 24.dp
                        )
                ) {
                    MovieList(
                        movies = movieTopRate,
                        label = stringResource(id = R.string.top_rated),
                        contentPaddingHorizontal = 20.dp,
                        onMovieItemClick = { movieId ->
                            viewModel.onEvent(
                                MoviesEvent.OpenMovieDetail(
                                    movieId
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}