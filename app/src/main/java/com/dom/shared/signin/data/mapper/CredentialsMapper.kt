package com.dom.shared.signin.data.mapper

import com.dom.shared.signin.data.dto.CredentialsDto
import com.dom.shared.signin.domain.entity.Credentials
import org.apache.commons.codec.digest.DigestUtils

fun Credentials.toDto() = CredentialsDto(
    login = login,
    passwordHash = DigestUtils.md5Hex(password)
)