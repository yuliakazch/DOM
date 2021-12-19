package com.dom.shared.signin.data.api

import com.dom.shared.signin.data.dto.CredentialsDto
import com.dom.shared.signin.data.dto.TokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInApi {

    @POST("/api/Login/Token")
    suspend fun signIn(@Body credentials: CredentialsDto): TokenDto
}