package com.dom.shared.subject.data.repository

import com.dom.shared.subject.data.datasource.SubjectDataSource
import com.dom.shared.subject.data.mapper.toDto
import com.dom.shared.subject.data.mapper.toEntity
import com.dom.shared.subject.domain.entity.Subject
import com.dom.shared.subject.domain.repository.SubjectRepository
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val dataSource: SubjectDataSource
) : SubjectRepository {

    override suspend fun get(id: Int): Subject =
        dataSource.get(id).toEntity()

    override suspend fun create(subject: Subject) {
        dataSource.create(subject.toDto())
    }

    override suspend fun update(subject: Subject) {
        dataSource.update(subject.toDto())
    }

    override suspend fun delete(id: Int) {
        dataSource.delete(id)
    }
}