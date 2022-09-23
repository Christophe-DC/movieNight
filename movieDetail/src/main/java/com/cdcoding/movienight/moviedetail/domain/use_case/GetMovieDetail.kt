package com.cdcoding.movienight.moviedetail.domain.use_case

import com.cdcoding.movienight.database.data.model.MovieDetail
import com.cdcoding.movienight.database.domain.repository.MovieDetailRepository

class GetMovieDetail(
    private val repository: MovieDetailRepository
) {

    suspend operator fun invoke(id: Int): MovieDetail? {
        return repository.getMovieDetailById(id)
    }
}