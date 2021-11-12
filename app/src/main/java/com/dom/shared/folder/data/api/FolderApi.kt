package com.dom.shared.folder.data.api

import com.dom.shared.folder.data.dto.FolderDto
import com.dom.shared.folder.data.dto.FoldersResultDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FolderApi {

	@GET("/api/Folders/Get")
	suspend fun get(): FoldersResultDto

	@POST
	suspend fun create(@Body folder: FolderDto)
}