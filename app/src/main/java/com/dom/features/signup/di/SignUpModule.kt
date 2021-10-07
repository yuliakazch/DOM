package com.dom.features.signup.di

import com.dom.features.signup.data.api.SignUpApi
import com.dom.features.signup.data.repository.SignUpRepositoryImpl
import com.dom.features.signup.domain.repository.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SignUpModule {

    @Provides
    fun provideSignUpRepository(
        repositoryImpl: SignUpRepositoryImpl
    ): SignUpRepository = repositoryImpl

    @Provides
    fun provideSignUpApi(retrofit: Retrofit) =
        retrofit.create(SignUpApi::class.java)
}