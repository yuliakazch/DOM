package com.dom.features.subject.create.presentation

data class SubjectCreateData(
    val name: String = "",
    val note: String = "",
    val price: String = "",
    val amount: String = "",
    val private: Boolean = false,
)