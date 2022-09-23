package com.cdcoding.movienight.movies.presentation


sealed class MoviesEvent {
    data class  OpenMovieDetail(val movieId: Int?): MoviesEvent()
}

