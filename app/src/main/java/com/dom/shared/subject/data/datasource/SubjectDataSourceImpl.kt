package com.dom.shared.subject.data.datasource

import com.dom.shared.subject.data.api.SubjectApi
import com.dom.shared.subject.data.dto.SubjectDto
import javax.inject.Inject

class SubjectDataSourceImpl @Inject constructor(
    private val api: SubjectApi
) : SubjectDataSource {

    override suspend fun get(id: Int): SubjectDto =
        api.get(id).result

    override suspend fun create(subject: SubjectDto) {
        api.create(subject)
    }

    override suspend fun update(subject: SubjectDto) {
        api.update(subject)
    }

    override suspend fun delete(id: Int) {
        api.delete(id)
    }
}