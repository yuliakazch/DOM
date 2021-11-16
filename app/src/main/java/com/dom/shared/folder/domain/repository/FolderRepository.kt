package com.dom.shared.folder.domain.repository

import com.dom.shared.folder.domain.entity.Folder

interface FolderRepository {

	suspend fun get(): List<Folder>

	suspend fun create(folder: Folder)

	suspend fun update(folder: Folder)

	suspend fun delete(id: Int)
}