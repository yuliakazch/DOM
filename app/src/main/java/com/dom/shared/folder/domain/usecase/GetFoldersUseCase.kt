package com.dom.shared.folder.domain.usecase

import com.dom.shared.folder.domain.entity.Folder
import com.dom.shared.folder.domain.repository.FolderRepository
import javax.inject.Inject

class GetFoldersUseCase @Inject constructor(private val repository: FolderRepository) {

	suspend operator fun invoke(): List<Folder> =
		repository.get()
}