package com.dom.shared.user.domain.entity

data class PasswordData(
    val oldPassword: String,
    val newPassword: String,
)