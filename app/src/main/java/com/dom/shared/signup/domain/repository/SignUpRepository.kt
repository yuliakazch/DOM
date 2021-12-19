package com.dom.shared.signup.domain.repository

import com.dom.shared.signup.domain.entity.SignUpData

interface SignUpRepository {

    suspend fun signUp(data: SignUpData)
}