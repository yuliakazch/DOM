package com.dom.shared.signup.data.api

import com.dom.shared.signup.data.dto.SignUpDataDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

    @POST("/api/User/RegisterUser")
    suspend fun signUp(@Body data: SignUpDataDto)
}