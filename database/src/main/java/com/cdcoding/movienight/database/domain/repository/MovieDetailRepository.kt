package com.cdcoding.movienight.database.domain.repository

import com.cdcoding.movienight.database.data.model.MovieDetail

interface MovieDetailRepository {

    suspend fun getMovieDetailById(id: Int): MovieDetail?

    suspend fun insertMovieDetail(movieDetail: MovieDetail)

    suspend fun deleteMovieDetail(movieDetail: MovieDetail)
}