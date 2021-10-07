package com.dom.features.signup.data.repository

import com.dom.features.signup.data.api.SignUpApi
import com.dom.features.signup.data.mapper.toDto
import com.dom.features.signup.domain.entity.SignUpData
import com.dom.features.signup.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val api: SignUpApi
) : SignUpRepository {

    override suspend fun signUp(data: SignUpData) {
        api.signUp(data.toDto())
    }
}