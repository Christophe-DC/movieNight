package com.cdcoding.movienight.movies.di

import com.cdcoding.movienight.database.domain.repository.AccountRepository
import com.cdcoding.movienight.database.domain.repository.MovieRepository
import com.cdcoding.movienight.movies.domain.use_case.GetAccount
import com.cdcoding.movienight.movies.domain.use_case.GetMovies
import com.cdcoding.movienight.movies.domain.use_case.MovieUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieModule {

    @Provides
    @Singleton
    fun provideMovieUseCases(
        movieRepository: MovieRepository,
        accountRepository: AccountRepository
    ): MovieUseCases {
        return MovieUseCases(
            getMovies = GetMovies(movieRepository),
            getAccount = GetAccount(accountRepository)
        )
    }
}