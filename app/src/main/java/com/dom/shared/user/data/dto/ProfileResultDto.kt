package com.dom.shared.user.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileResultDto(
    @SerializedName("result") val result: ProfileDto,
)