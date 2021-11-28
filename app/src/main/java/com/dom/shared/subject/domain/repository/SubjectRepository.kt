package com.dom.shared.subject.domain.repository

import com.dom.shared.subject.domain.entity.Subject

interface SubjectRepository {

    suspend fun get(id: Int): Subject

    suspend fun create(subject: Subject)

    suspend fun update(subject: Subject)

    suspend fun delete(id: Int)
}