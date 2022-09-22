package com.cdcoding.movienight.database.data.repository

import com.cdcoding.movienight.database.data.MovieDetailDao
import com.cdcoding.movienight.database.data.model.Movie
import com.cdcoding.movienight.database.data.model.MovieDetail
import com.cdcoding.movienight.database.domain.repository.MovieDetailRepository

internal class MovieDetailRepositoryImpl(
    val dao: MovieDetailDao
) : MovieDetailRepository {

    override suspend fun getMovieDetailById(id: Int): MovieDetail? {
        return dao.getMovieDetailById(id)
    }

    override suspend fun insertMovieDetail(movieDetail: MovieDetail) {
        return dao.insertMovieDetail(movieDetail)
    }


    override suspend fun deleteMovieDetail(movieDetail: MovieDetail) {
        return dao.deleteMovieDetail(movieDetail)
    }
}