package com.dom.shared.folder.domain.repository

import com.dom.shared.folder.domain.entity.Folder
import com.dom.shared.folder.domain.entity.FolderInfo

interface FolderRepository {

    suspend fun get(): List<Folder>

    suspend fun getInfo(id: Int): FolderInfo

    suspend fun create(folder: Folder)

    suspend fun update(folder: Folder)

    suspend fun delete(id: Int)
}