package com.dom.shared.logout.di

import com.dom.shared.logout.data.datasource.LogoutLocalDataSource
import com.dom.shared.logout.data.datasource.LogoutLocalDataSourceImpl
import com.dom.shared.logout.data.repository.LogoutRepositoryImpl
import com.dom.shared.logout.domain.repository.LogoutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LogoutModule {

    @Provides
    fun provideLogoutLocalDataSource(
        dataSourceImpl: LogoutLocalDataSourceImpl
    ): LogoutLocalDataSource = dataSourceImpl

    @Provides
    fun provideLogoutRepository(
        repositoryImpl: LogoutRepositoryImpl
    ): LogoutRepository = repositoryImpl
}