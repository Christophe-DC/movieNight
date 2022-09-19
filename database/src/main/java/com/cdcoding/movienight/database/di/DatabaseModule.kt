package com.cdcoding.movienight.database.di

import android.app.Application
import androidx.room.Room
import com.cdcoding.movienight.database.data.MovieNightDatabase
import com.cdcoding.movienight.database.data.repository.AccountRepositoryImpl
import com.cdcoding.movienight.database.data.repository.MovieRepositoryImpl
import com.cdcoding.movienight.database.domain.repository.AccountRepository
import com.cdcoding.movienight.database.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Provides
    @Singleton
    fun provideAccountDatabase(app: Application): MovieNightDatabase {
        return Room.databaseBuilder(
            app,
            MovieNightDatabase::class.java,
            MovieNightDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideAccountRepository(db: MovieNightDatabase): AccountRepository {
        return AccountRepositoryImpl(db.accountDao)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(db: MovieNightDatabase): MovieRepository {
        return MovieRepositoryImpl(db.movieDao)
    }

}