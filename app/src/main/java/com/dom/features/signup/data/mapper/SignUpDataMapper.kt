package com.dom.features.signup.data.mapper

import com.dom.features.signup.data.dto.SignUpDataDto
import com.dom.features.signup.domain.entity.SignUpData
import org.apache.commons.codec.digest.DigestUtils

fun SignUpData.toDto() = SignUpDataDto(
    login = login,
    passwordHash = DigestUtils.md5Hex(password)
)