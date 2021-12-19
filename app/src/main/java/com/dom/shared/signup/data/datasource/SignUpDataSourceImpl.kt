package com.dom.shared.signup.data.datasource

import com.dom.shared.signup.data.api.SignUpApi
import com.dom.shared.signup.data.dto.SignUpDataDto
import javax.inject.Inject

class SignUpDataSourceImpl @Inject constructor(
    private val api: SignUpApi
) : SignUpDataSource {

    override suspend fun signUp(data: SignUpDataDto) {
        api.signUp(data)
    }
}