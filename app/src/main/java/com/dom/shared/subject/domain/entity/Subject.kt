package com.dom.shared.subject.domain.entity

data class Subject(
    val id: Int = 0,
    val folderId: Int = 0,
    val name: String,
    val note: String,
    val price: Float,
    val amount: Int,
    val private: Boolean,
)