package com.dom.shared.folder.data.datasource

import com.dom.shared.folder.data.api.FolderApi
import com.dom.shared.folder.data.dto.FolderDto
import com.dom.shared.folder.data.mapper.toEntity
import javax.inject.Inject

class FolderDataSourceImpl @Inject constructor(
	private val api: FolderApi
) : FolderDataSource {

	override suspend fun get(): List<FolderDto> =
		api.get().result

	override suspend fun create(folder: FolderDto) {
		api.create(folder)
	}

	override suspend fun update(folder: FolderDto) {
		api.update(folder)
	}

	override suspend fun delete(id: Int) {
		api.delete(id)
	}
}