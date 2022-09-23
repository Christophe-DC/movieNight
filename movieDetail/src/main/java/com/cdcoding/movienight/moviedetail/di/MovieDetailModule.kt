package com.cdcoding.movienight.moviedetail.di

import com.cdcoding.movienight.database.domain.repository.MovieDetailRepository
import com.cdcoding.movienight.moviedetail.domain.use_case.GetMovieDetail
import com.cdcoding.movienight.moviedetail.domain.use_case.MovieDetailUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieDetailModule {

    @Provides
    @Singleton
    fun provideMovieDetailUseCases(
        movieDetailRepository: MovieDetailRepository
    ): MovieDetailUseCases {
        return MovieDetailUseCases(
            getMovieDetail = GetMovieDetail(movieDetailRepository)
        )
    }
}