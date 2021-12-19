package com.dom.shared.signup.data.repository

import com.dom.shared.signup.data.datasource.SignUpDataSource
import com.dom.shared.signup.data.mapper.toDto
import com.dom.shared.signup.domain.entity.SignUpData
import com.dom.shared.signup.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val dataSource: SignUpDataSource
) : SignUpRepository {

    override suspend fun signUp(data: SignUpData) {
        dataSource.signUp(data.toDto())
    }
}