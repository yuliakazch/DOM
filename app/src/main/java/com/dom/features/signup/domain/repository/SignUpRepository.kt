package com.dom.features.signup.domain.repository

import com.dom.features.signup.domain.entity.SignUpData

interface SignUpRepository {

    suspend fun signUp(data: SignUpData)
}