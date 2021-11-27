package com.dom.shared.folder.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubjectDto(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String?,
)