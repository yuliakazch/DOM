package com.dom.shared.folder.di

import com.dom.shared.folder.data.datasource.FolderDataSource
import com.dom.shared.folder.data.datasource.FolderDataSourceImpl
import com.dom.shared.folder.data.repository.FolderRepositoryImpl
import com.dom.shared.folder.domain.repository.FolderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FolderModule {

	@Provides
	fun provideFolderDataSource(
		dataSourceImpl: FolderDataSourceImpl
	): FolderDataSource = dataSourceImpl

	@Provides
	fun provideFolderRepository(
		repositoryImpl: FolderRepositoryImpl
	): FolderRepository = repositoryImpl
}