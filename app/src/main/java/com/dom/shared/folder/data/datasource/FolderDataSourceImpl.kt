package com.dom.shared.folder.data.datasource

import com.dom.shared.folder.data.api.FolderApi
import com.dom.shared.folder.data.dto.FolderDto
import javax.inject.Inject

class FolderDataSourceImpl @Inject constructor(
	private val api: FolderApi
) : FolderDataSource {

	override suspend fun get(): List<FolderDto> =
		api.get().result
}