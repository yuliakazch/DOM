package com.dom.shared.folder.data.repository

import com.dom.shared.folder.data.datasource.FolderDataSource
import com.dom.shared.folder.data.mapper.toDto
import com.dom.shared.folder.data.mapper.toEntityList
import com.dom.shared.folder.domain.entity.Folder
import com.dom.shared.folder.domain.repository.FolderRepository
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
	private val dataSource: FolderDataSource
) : FolderRepository {

	override suspend fun get(): List<Folder> =
		dataSource.get().toEntityList()

	override suspend fun create(folder: Folder) {
		dataSource.create(folder.toDto())
	}

	override suspend fun update(folder: Folder) {
		dataSource.update(folder.toDto())
	}

	override suspend fun delete(id: Int) {
		dataSource.delete(id)
	}
}