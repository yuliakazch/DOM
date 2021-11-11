package com.dom.shared.folder.data.api

import com.dom.shared.folder.data.dto.FoldersResultDto
import retrofit2.http.GET

interface FolderApi {

	@GET("/api/Folders/Get")
	suspend fun get(): FoldersResultDto
}