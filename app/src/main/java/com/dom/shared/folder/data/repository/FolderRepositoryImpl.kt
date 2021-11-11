package com.dom.shared.folder.data.repository

import com.dom.shared.folder.data.datasource.FolderDataSource
import com.dom.shared.folder.data.mapper.toEntityList
import com.dom.shared.folder.domain.entity.Folder
import com.dom.shared.folder.domain.repository.FolderRepository
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
	private val dataSource: FolderDataSource
) : FolderRepository {

	override suspend fun get(): List<Folder> =
		dataSource.get().toEntityList()
}