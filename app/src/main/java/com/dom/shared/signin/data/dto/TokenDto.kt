package com.dom.shared.signin.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenDto(
    @SerializedName("access_token") val value: String
)