package com.dom.shared.subject.domain.usecase

import com.dom.shared.subject.domain.entity.Subject
import com.dom.shared.subject.domain.repository.SubjectRepository
import javax.inject.Inject

class GetSubjectUseCase @Inject constructor(private val repository: SubjectRepository) {

    suspend operator fun invoke(id: Int): Subject =
        repository.get(id)
}