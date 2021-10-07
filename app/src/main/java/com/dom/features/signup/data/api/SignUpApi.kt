package com.dom.features.signup.data.api

import com.dom.features.signup.data.dto.SignUpDataDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

    @POST("/api/User/RegisterUser")
    suspend fun signUp(@Body data: SignUpDataDto)
}