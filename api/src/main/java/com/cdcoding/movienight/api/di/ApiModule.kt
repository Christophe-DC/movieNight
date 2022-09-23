package com.cdcoding.movienight.api.di

import com.cdcoding.movienight.api.data.model.MovieApi
import com.cdcoding.movienight.api.data.model.repository.MovieRemoteRepositoryImpl
import com.cdcoding.movienight.api.domain.repository.MovieRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    internal fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }


    @Provides
    @Singleton
    internal fun provideMovieRemoteRepository(movieApi: MovieApi): MovieRemoteRepository {
        return MovieRemoteRepositoryImpl(movieApi)
    }
}