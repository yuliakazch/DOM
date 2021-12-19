package com.dom.shared.signin.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CredentialsDto(
    @SerializedName("username") val login: String,
    @SerializedName("password_hash") val passwordHash: String
)