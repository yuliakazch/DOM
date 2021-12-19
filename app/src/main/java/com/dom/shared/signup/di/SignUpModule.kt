package com.dom.shared.signup.di

import com.dom.shared.signup.data.api.SignUpApi
import com.dom.shared.signup.data.datasource.SignUpDataSource
import com.dom.shared.signup.data.datasource.SignUpDataSourceImpl
import com.dom.shared.signup.data.repository.SignUpRepositoryImpl
import com.dom.shared.signup.domain.repository.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SignUpModule {

    @Provides
    fun provideSignUpApi(retrofit: Retrofit) =
        retrofit.create(SignUpApi::class.java)

    @Provides
    fun provideSignUpDataSource(
        dataSourceImpl: SignUpDataSourceImpl
    ): SignUpDataSource = dataSourceImpl

    @Provides
    fun provideSignUpRepository(
        repositoryImpl: SignUpRepositoryImpl
    ): SignUpRepository = repositoryImpl
}