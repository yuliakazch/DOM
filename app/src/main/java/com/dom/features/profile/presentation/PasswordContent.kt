package com.dom.features.profile.presentation

data class PasswordContent(
    val currentPassword: String = "",
    val newPassword: String = "",
    val newPasswordAgain: String = "",
)