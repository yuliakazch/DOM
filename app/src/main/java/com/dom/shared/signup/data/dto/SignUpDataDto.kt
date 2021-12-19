package com.dom.shared.signup.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpDataDto(
    @SerializedName("Username") val login: String,
    @SerializedName("Password_hash") val passwordHash: String
)