package com.dom.features.signup.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpDataDto(
    @Json(name = "Username") @SerializedName("Username") val login: String,
    @Json(name = "Password_hash") @SerializedName("Password_hash") val passwordHash: String
)