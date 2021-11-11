package com.dom.shared.folder.domain.repository

import com.dom.shared.folder.domain.entity.Folder

interface FolderRepository {

	suspend fun get(): List<Folder>
}