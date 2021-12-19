package com.dom.shared.user.data.datasource

import com.dom.shared.user.data.api.UserApi
import com.dom.shared.user.data.dto.PasswordDataDto
import com.dom.shared.user.data.dto.ProfileDto
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val api: UserApi
) : UserDataSource {

    override suspend fun getProfile(): ProfileDto =
        api.getProfile().result

    override suspend fun delete() {
        api.delete()
    }

    override suspend fun changePassword(data: PasswordDataDto) {
        api.changePassword(data)
    }
}