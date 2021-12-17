package com.dom.shared.user.di

import com.dom.shared.user.data.api.UserApi
import com.dom.shared.user.data.datasource.UserDataSource
import com.dom.shared.user.data.datasource.UserDataSourceImpl
import com.dom.shared.user.data.repository.UserRepositoryImpl
import com.dom.shared.user.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class UserModule {

    @Provides
    fun provideUserApi(retrofit: Retrofit) =
        retrofit.create(UserApi::class.java)

    @Provides
    fun provideUserDataSource(
        dataSourceImpl: UserDataSourceImpl
    ): UserDataSource = dataSourceImpl

    @Provides
    fun provideUserRepository(
        repositoryImpl: UserRepositoryImpl
    ): UserRepository = repositoryImpl
}