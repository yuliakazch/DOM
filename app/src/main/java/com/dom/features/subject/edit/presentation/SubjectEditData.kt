package com.dom.features.subject.edit.presentation

data class SubjectEditData(
    val id: Int,
    val name: String,
    val note: String,
    val price: String,
    val amount: String,
    val private: Boolean,
)