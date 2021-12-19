package com.dom.shared.signin.data.datasource

import com.dom.shared.signin.data.dto.CredentialsDto
import com.dom.shared.signin.data.dto.TokenDto

interface SignInDataSource {

    suspend fun signIn(credentials: CredentialsDto): TokenDto
}