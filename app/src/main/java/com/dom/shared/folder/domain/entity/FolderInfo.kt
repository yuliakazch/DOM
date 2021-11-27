package com.dom.shared.folder.domain.entity

data class FolderInfo(
    val id: Int = 0,
    val name: String?,
    val items: List<Subject>,
)