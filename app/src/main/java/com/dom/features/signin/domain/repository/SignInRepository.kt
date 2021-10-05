package com.dom.features.signin.domain.repository

import com.dom.features.signin.domain.entity.Credentials

interface SignInRepository {

    suspend fun signIn(credentials: Credentials)
}