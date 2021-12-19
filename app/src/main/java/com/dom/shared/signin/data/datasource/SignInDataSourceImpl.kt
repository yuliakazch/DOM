package com.dom.shared.signin.data.datasource

import com.dom.shared.signin.data.api.SignInApi
import com.dom.shared.signin.data.dto.CredentialsDto
import com.dom.shared.signin.data.dto.TokenDto
import javax.inject.Inject

class SignInDataSourceImpl @Inject constructor(
    private val api: SignInApi
) : SignInDataSource {

    override suspend fun signIn(credentials: CredentialsDto): TokenDto =
        api.signIn(credentials)
}