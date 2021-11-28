package com.dom.shared.folder.data.dto

import com.dom.shared.subject.data.dto.SubjectDto
import com.google.gson.annotations.SerializedName

data class FolderInfoDto(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String?,
    @SerializedName("items") val items: List<SubjectDto>,
)