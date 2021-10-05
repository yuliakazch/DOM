package com.dom.features.signin.data.api

import com.dom.features.signin.data.dto.TokenDto
import retrofit2.http.POST
import retrofit2.http.Path

interface SignInApi {

    @POST("/api/Login/Token/{login}/{password_hash}")
    suspend fun signIn(
        @Path("login") login: String,
        @Path("password_hash") password: String
    ): TokenDto
}