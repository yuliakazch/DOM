package com.dom.features.signin.data.mapper

import com.dom.features.signin.data.dto.CredentialsDto
import com.dom.features.signin.domain.entity.Credentials
import org.apache.commons.codec.digest.DigestUtils

fun Credentials.toDto() = CredentialsDto(
    login = login,
    passwordHash = DigestUtils.md5Hex(password)
)