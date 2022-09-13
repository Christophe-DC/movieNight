package com.cdcoding.movienight.login.di

import android.app.Application
import androidx.room.Room
import com.cdcoding.movienight.login.data.data_source.MovieNightDatabase
import com.cdcoding.movienight.login.data.repository.AccountRepositoryImpl
import com.cdcoding.movienight.login.domain.repository.AccountRepository
import com.cdcoding.movienight.login.domain.use_case.AccountUseCases
import com.cdcoding.movienight.login.domain.use_case.AddAccount
import com.cdcoding.movienight.login.domain.use_case.GetAccounts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

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
    fun provideAccountUseCases(repository: AccountRepository): AccountUseCases {
        return AccountUseCases(
            getAccounts = GetAccounts(repository),
            addAccount = AddAccount(repository)
        )
    }
}