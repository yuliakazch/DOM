package com.dom.shared.folder.data.datasource

import com.dom.shared.folder.data.dto.FolderDto
import com.dom.shared.folder.data.dto.FolderInfoDto

interface FolderDataSource {

	suspend fun get(): List<FolderDto>

	suspend fun getInfo(id: Int): FolderInfoDto

	suspend fun create(folder: FolderDto)

	suspend fun update(folder: FolderDto)

	suspend fun delete(id: Int)
}