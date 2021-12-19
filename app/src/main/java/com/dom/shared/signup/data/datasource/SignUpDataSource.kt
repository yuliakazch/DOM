package com.dom.shared.signup.data.datasource

import com.dom.shared.signup.data.dto.SignUpDataDto

interface SignUpDataSource {

    suspend fun signUp(data: SignUpDataDto)
}