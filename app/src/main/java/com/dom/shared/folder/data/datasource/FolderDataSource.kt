package com.dom.shared.folder.data.datasource

import com.dom.shared.folder.data.dto.FolderDto

interface FolderDataSource {

	suspend fun get(): List<FolderDto>

	suspend fun create(folder: FolderDto)
}