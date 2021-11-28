package com.dom.shared.subject.data.api

import com.dom.shared.subject.data.dto.SubjectDto
import com.dom.shared.subject.data.dto.SubjectResultDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SubjectApi {

    @GET("/api/Items/Get/{id}")
    suspend fun get(@Path("id") id: Int): SubjectResultDto

    @POST("/api/Items/Create")
    suspend fun create(@Body subject: SubjectDto)

    @POST("/api/Items/Update")
    suspend fun update(@Body subject: SubjectDto)

    @GET("/api/Items/Delete/{id}")
    suspend fun delete(@Path("id") id: Int)
}