package com.dom.shared.user.domain.repository

import com.dom.shared.user.domain.entity.PasswordData
import com.dom.shared.user.domain.entity.Profile

interface UserRepository {

    suspend fun getProfile(): Profile

    suspend fun delete()

    suspend fun changePassword(data: PasswordData)
}