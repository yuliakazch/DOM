package com.dom.shared.subject.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubjectResultDto(
    @SerializedName("result") val result: SubjectDto
)
