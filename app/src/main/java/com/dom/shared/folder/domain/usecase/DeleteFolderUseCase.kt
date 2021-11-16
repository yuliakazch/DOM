package com.dom.shared.folder.domain.usecase

import com.dom.shared.folder.domain.repository.FolderRepository
import javax.inject.Inject

class DeleteFolderUseCase @Inject constructor(private val repository: FolderRepository) {

	suspend operator fun invoke(id: Int) {
		repository.delete(id)
	}
}