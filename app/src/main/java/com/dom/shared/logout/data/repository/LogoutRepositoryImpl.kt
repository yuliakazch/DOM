package com.dom.shared.logout.data.repository

import com.dom.shared.logout.data.datasource.LogoutLocalDataSource
import com.dom.shared.logout.domain.repository.LogoutRepository
import javax.inject.Inject

class LogoutRepositoryImpl @Inject constructor(
    private val dataSource: LogoutLocalDataSource
) : LogoutRepository {

    override fun clearToken() {
        dataSource.clearToken()
    }
}