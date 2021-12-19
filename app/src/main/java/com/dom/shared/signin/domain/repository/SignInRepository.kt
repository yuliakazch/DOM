package com.dom.shared.signin.domain.repository

import com.dom.shared.signin.domain.entity.Credentials

interface SignInRepository {

    suspend fun signIn(credentials: Credentials)
}