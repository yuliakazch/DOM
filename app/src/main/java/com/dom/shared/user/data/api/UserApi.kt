package com.dom.shared.user.data.api

import com.dom.shared.user.data.dto.PasswordDataDto
import com.dom.shared.user.data.dto.ProfileResultDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("/api/User/Get")
    suspend fun getProfile(): ProfileResultDto

    @GET("/api/User/DeleteUser")
    suspend fun delete()

    @POST("/api/User/ChangePasswordWithValidation")
    suspend fun changePassword(@Body data: PasswordDataDto)
}