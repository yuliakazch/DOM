package com.dom.shared.subject.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubjectDto(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String?,
    @SerializedName("note") val note: String?,
    @SerializedName("price") val price: Float?,
    @SerializedName("amount") val amount: Int?,
    @SerializedName("amount_type_id") val amountTypeId: Int?,
    @SerializedName("is_private") val private: Boolean,
)