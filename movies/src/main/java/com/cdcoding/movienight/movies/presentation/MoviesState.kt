package com.cdcoding.movienight.movies.presentation

import com.cdcoding.movienight.database.data.model.Account
import com.cdcoding.movienight.database.data.model.Movie


data class MoviesState(
    val account: Account? = null,
    val movies: List<Movie> = emptyList()
)
