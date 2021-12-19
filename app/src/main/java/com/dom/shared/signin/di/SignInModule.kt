package com.dom.shared.signin.di

import com.dom.shared.signin.data.api.SignInApi
import com.dom.shared.signin.data.datasource.SignInDataSource
import com.dom.shared.signin.data.datasource.SignInDataSourceImpl
import com.dom.shared.signin.data.repository.SignInRepositoryImpl
import com.dom.shared.signin.domain.repository.SignInRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SignInModule {

    @Provides
    fun provideSignInApi(retrofit: Retrofit) =
        retrofit.create(SignInApi::class.java)

    @Provides
    fun provideSignInDataSource(
        dataSourceImpl: SignInDataSourceImpl
    ): SignInDataSource = dataSourceImpl

    @Provides
    fun provideSignInRepository(
        repositoryImpl: SignInRepositoryImpl
    ): SignInRepository = repositoryImpl
}