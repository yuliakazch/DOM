package com.dom.features.signin.di

import com.dom.features.signin.data.api.SignInApi
import com.dom.features.signin.data.repository.SignInRepositoryImpl
import com.dom.features.signin.domain.repository.SignInRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SignInModule {

    @Provides
    fun provideSignInRepository(
        repositoryImpl: SignInRepositoryImpl
    ): SignInRepository = repositoryImpl

    @Provides
    fun provideSignInApi(retrofit: Retrofit) =
        retrofit.create(SignInApi::class.java)
}