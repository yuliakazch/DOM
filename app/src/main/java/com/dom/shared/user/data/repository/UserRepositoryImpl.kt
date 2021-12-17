package com.dom.shared.user.data.repository

import com.dom.shared.user.data.datasource.UserDataSource
import com.dom.shared.user.data.mapper.toEntity
import com.dom.shared.user.domain.entity.Profile
import com.dom.shared.user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserDataSource
) : UserRepository {

    override suspend fun getProfile(): Profile =
        dataSource.getProfile().toEntity()

    override suspend fun delete() {
        dataSource.delete()
    }
}