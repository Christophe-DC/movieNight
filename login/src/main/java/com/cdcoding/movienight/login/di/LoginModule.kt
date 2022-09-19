package com.cdcoding.movienight.login.di

import com.cdcoding.movienight.database.domain.repository.AccountRepository
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
    fun provideAccountUseCases(repository: AccountRepository): AccountUseCases {
        return AccountUseCases(
            getAccounts = GetAccounts(repository),
            addAccount = AddAccount(repository)
        )
    }
}