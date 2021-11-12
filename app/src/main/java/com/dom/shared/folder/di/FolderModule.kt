package com.dom.shared.folder.di

import com.dom.shared.folder.data.api.FolderApi
import com.dom.shared.folder.data.datasource.FolderDataSource
import com.dom.shared.folder.data.datasource.FolderDataSourceImpl
import com.dom.shared.folder.data.repository.FolderRepositoryImpl
import com.dom.shared.folder.domain.repository.FolderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class FolderModule {

	@Provides
	fun provideFolderApi(retrofit: Retrofit) =
		retrofit.create(FolderApi::class.java)

	@Provides
	fun provideFolderDataSource(
		dataSourceImpl: FolderDataSourceImpl
	): FolderDataSource = dataSourceImpl

	@Provides
	fun provideFolderRepository(
		repositoryImpl: FolderRepositoryImpl
	): FolderRepository = repositoryImpl
}