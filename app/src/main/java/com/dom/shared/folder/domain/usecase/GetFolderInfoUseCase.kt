package com.dom.shared.folder.domain.usecase

import com.dom.shared.folder.domain.entity.FolderInfo
import com.dom.shared.folder.domain.repository.FolderRepository
import javax.inject.Inject

class GetFolderInfoUseCase @Inject constructor(private val repository: FolderRepository) {

    suspend operator fun invoke(id: Int): FolderInfo =
        repository.getInfo(id)
}