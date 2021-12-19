package com.dom.shared.signup.data.mapper

import com.dom.shared.signup.data.dto.SignUpDataDto
import com.dom.shared.signup.domain.entity.SignUpData
import org.apache.commons.codec.digest.DigestUtils

fun SignUpData.toDto() = SignUpDataDto(
    login = login,
    passwordHash = DigestUtils.md5Hex(password)
)