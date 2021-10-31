package com.dom.features.signin.data.api

import com.dom.features.signin.data.dto.CredentialsDto
import com.dom.features.signin.data.dto.TokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInApi {

    @POST("/api/Login/Token")
    suspend fun signIn(
        @Body credentialsDto: CredentialsDto,
    ): TokenDto
}