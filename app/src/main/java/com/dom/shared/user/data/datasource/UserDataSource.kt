package com.dom.shared.user.data.datasource

import com.dom.shared.user.data.dto.ProfileDto

interface UserDataSource {

    suspend fun getProfile(): ProfileDto

    suspend fun delete()
}