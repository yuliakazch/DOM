package com.dom.shared.folder.data.api

import com.dom.shared.folder.data.dto.FolderDto
import com.dom.shared.folder.data.dto.FolderInfoResultDto
import com.dom.shared.folder.data.dto.FoldersResultDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FolderApi {

	@GET("/api/Folders/Get")
	suspend fun get(): FoldersResultDto

	@GET("/api/Folders/Get/{id}")
	suspend fun getInfo(@Path("id") id: Int): FolderInfoResultDto

	@POST("/api/Folders/Create")
	suspend fun create(@Body folder: FolderDto)

	@POST("/api/Folders/Update")
	suspend fun update(@Body folder: FolderDto)

	@GET("/api/Folders/Delete/{id}")
	suspend fun delete(@Path("id") id: Int)
}