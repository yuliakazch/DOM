package com.dom.shared.folder.domain.entity

import com.dom.shared.subject.domain.entity.Subject

data class FolderInfo(
    val id: Int = 0,
    val name: String?,
    val subjects: List<Subject>,
)