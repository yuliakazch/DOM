package com.dom.shared.subject.di

import com.dom.shared.subject.data.api.SubjectApi
import com.dom.shared.subject.data.datasource.SubjectDataSource
import com.dom.shared.subject.data.datasource.SubjectDataSourceImpl
import com.dom.shared.subject.data.repository.SubjectRepositoryImpl
import com.dom.shared.subject.domain.repository.SubjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SubjectModule {

    @Provides
    fun provideSubjectApi(retrofit: Retrofit) =
        retrofit.create(SubjectApi::class.java)

    @Provides
    fun provideSubjectDataSource(
        dataSourceImpl: SubjectDataSourceImpl
    ): SubjectDataSource = dataSourceImpl

    @Provides
    fun provideSubjectRepository(
        repositoryImpl: SubjectRepositoryImpl
    ): SubjectRepository = repositoryImpl
}