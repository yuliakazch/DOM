package com.dom.shared.user.data.api

import com.dom.shared.user.data.dto.ProfileResultDto
import retrofit2.http.GET

interface UserApi {

    @GET("/api/User/Get")
    suspend fun getProfile(): ProfileResultDto

    @GET("/api/User/DeleteUser")
    suspend fun delete()
}