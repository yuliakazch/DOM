package com.dom.shared.folder.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FolderInfoResultDto(
    @SerializedName("result") val result: FolderInfoDto
)