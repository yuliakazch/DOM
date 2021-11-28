package com.dom.shared.subject.domain.usecase

import com.dom.shared.subject.domain.repository.SubjectRepository
import javax.inject.Inject

class DeleteSubjectUseCase @Inject constructor(private val repository: SubjectRepository) {

    suspend operator fun invoke(id: Int) {
        repository.delete(id)
    }
}