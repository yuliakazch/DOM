package com.dom.shared.subject.data.datasource

import com.dom.shared.subject.data.dto.SubjectDto

interface SubjectDataSource {

    suspend fun get(id: Int): SubjectDto

    suspend fun create(subject: SubjectDto)

    suspend fun update(subject: SubjectDto)

    suspend fun delete(id: Int)
}